class PlaceHold {
  void init(Device device, ImageData image) {
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    int width = image.width;
    int height = image.height;
    PaletteData palette = image.palette;
    if (!((((((image.depth == 1) || (image.depth == 2)) || (image.depth == 4))
                || (image.depth == 8))
            && (!palette.isDirect))
        || ((image.depth == 8)
            || ((((image.depth == 16) || (image.depth == 24)) || (image.depth == 32))
                && palette.isDirect)))) {
      SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    int pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, width, height);
    if (pixbuf == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
    int data = OS.gdk_pixbuf_get_pixels(pixbuf);
    byte[] buffer = image.data;
    if ((((((!palette.isDirect) || (image.depth != 24)) || (stride != image.bytesPerLine))
                || (palette.redMask != 0xff0000))
            || (palette.greenMask != 0xff00))
        || (palette.blueMask != 0xff)) {
      buffer = new byte[stride * height];
      if (palette.isDirect) {
        ImageData.blit(
            BLIT_SRC,
            image.data,
            image.depth,
            image.bytesPerLine,
            image.getByteOrder(),
            0,
            0,
            width,
            height,
            palette.redMask,
            palette.greenMask,
            palette.blueMask,
            ALPHA_OPAQUE,
            null,
            0,
            0,
            0,
            buffer,
            24,
            stride,
            MSB_FIRST,
            0,
            0,
            width,
            height,
            0xff0000,
            0xff00,
            0xff,
            false,
            false);
      } else {
        RGB[] rgbs = palette.getRGBs();
        int length = rgbs.length;
        byte[] srcReds = new byte[length];
        byte[] srcGreens = new byte[length];
        byte[] srcBlues = new byte[length];
        for (int i = 0; i < rgbs.length; i++) {
          RGB rgb = rgbs[i];
          if (rgb == null) {
            continue;
          }
          srcReds[i] = ((byte) (rgb.red));
          srcGreens[i] = ((byte) (rgb.green));
          srcBlues[i] = ((byte) (rgb.blue));
        }
        ImageData.blit(
            BLIT_SRC,
            image.data,
            image.depth,
            image.bytesPerLine,
            image.getByteOrder(),
            0,
            0,
            width,
            height,
            srcReds,
            srcGreens,
            srcBlues,
            ALPHA_OPAQUE,
            null,
            0,
            0,
            0,
            buffer,
            24,
            stride,
            MSB_FIRST,
            0,
            0,
            width,
            height,
            0xff0000,
            0xff00,
            0xff,
            false,
            false);
      }
    }
    OS.memmove(data, buffer, stride * height);
    int pixmap = OS.gdk_pixmap_new(OS.GDK_ROOT_PARENT(), width, height, -1);
    if (pixmap == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int gdkGC = OS.gdk_gc_new(pixmap);
    if (gdkGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.gdk_pixbuf_render_to_drawable(
        pixbuf, pixmap, gdkGC, 0, 0, 0, 0, width, height, GDK_RGB_DITHER_NORMAL, 0, 0);
    OS.g_object_unref(gdkGC);
    OS.g_object_unref(pixbuf);
    boolean isIcon = image.getTransparencyType() == SWT.TRANSPARENCY_MASK;
    if (isIcon || (image.transparentPixel != (-1))) {
      if (image.transparentPixel != (-1)) {
        RGB rgb = null;
        if (palette.isDirect) {
          rgb = palette.getRGB(image.transparentPixel);
        } else if (image.transparentPixel < palette.colors.length) {
          rgb = palette.getRGB(image.transparentPixel);
        }
        if (rgb != null) {
          transparentPixel = ((rgb.red << 16) | (rgb.green << 8)) | rgb.blue;
        }
      }
      int mask = createMask(image, isIcon);
      if (mask == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      this.mask = mask;
      if (isIcon) {
        this.type = SWT.ICON;
      } else {
        this.type = SWT.BITMAP;
      }
    } else {
      this.type = SWT.BITMAP;
      this.mask = 0;
      this.alpha = image.alpha;
      if ((image.alpha == (-1)) && (image.alphaData != null)) {
        this.alphaData = new byte[image.alphaData.length];
        System.arraycopy(image.alphaData, 0, this.alphaData, 0, alphaData.length);
      }
      if (device.useXRender && ((alpha != (-1)) || (alphaData != null))) {
        mask = OS.gdk_pixmap_new(0, alpha != (-1) ? 1 : width, alpha != (-1) ? 1 : height, 8);
        if (mask == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        int gc = OS.gdk_gc_new(mask);
        if (alpha != (-1)) {
          GdkColor color = new GdkColor();
          color.pixel = ((alpha & 0xff) << 8) | (alpha & 0xff);
          OS.gdk_gc_set_foreground(gc, color);
          OS.gdk_draw_rectangle(mask, gc, 1, 0, 0, 1, 1);
        } else {
          int imagePtr = OS.gdk_drawable_get_image(mask, 0, 0, width, height);
          GdkImage gdkImage = new GdkImage();
          OS.memmove(gdkImage, imagePtr);
          if (gdkImage.bpl == width) {
            OS.memmove(gdkImage.mem, alphaData, alphaData.length);
          } else {
            byte[] line = new byte[gdkImage.bpl];
            for (int y = 0; y < height; y++) {
              System.arraycopy(alphaData, width * y, line, 0, width);
              OS.memmove(gdkImage.mem + (gdkImage.bpl * y), line, gdkImage.bpl);
            }
          }
          OS.gdk_draw_image(mask, gc, imagePtr, 0, 0, 0, 0, width, height);
          OS.g_object_unref(imagePtr);
        }
        OS.g_object_unref(gc);
      }
    }
    this.pixmap = pixmap;
  }
}
