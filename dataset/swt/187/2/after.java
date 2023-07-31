class PlaceHold {
  public ImageData getImageData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    ImageData data;
    if (OS.USE_CAIRO_SURFACE) {
      int width = this.width;
      int height = this.height;
      int stride = Cairo.cairo_format_stride_for_width(CAIRO_FORMAT_ARGB32, width);
      byte[] srcData = new byte[stride * height];
      int oa;
      int or;
      int og;
      int ob;
      if (OS.BIG_ENDIAN) {
        oa = 0;
        or = 1;
        og = 2;
        ob = 3;
      } else {
        oa = 3;
        or = 2;
        og = 1;
        ob = 0;
      }
      OS.memmove(srcData, this.surfaceData, srcData.length);
      PaletteData palette = new PaletteData(0xff0000, 0xff00, 0xff);
      data = new ImageData(width, height, 32, palette, 4, srcData);
      int offset = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++, offset += 4) {
          int a = srcData[offset + oa] & 0xff;
          int r = srcData[offset + or] & 0xff;
          int g = srcData[offset + og] & 0xff;
          int b = srcData[offset + ob] & 0xff;
          srcData[offset + 3] = ((byte) (a));
          if (a != 0) {
            srcData[offset + 2] = ((byte) ((r / ((float) (a))) * 0xff));
            srcData[offset + 1] = ((byte) ((g / ((float) (a))) * 0xff));
            srcData[offset + 0] = ((byte) ((b / ((float) (a))) * 0xff));
          }
        }
      }
    } else {
      int[] w = new int[1];
      int[] h = new int[1];
      OS.gdk_drawable_get_size(pixmap, w, h);
      int width = w[0];
      int height = h[0];
      int pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, width, height);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_pixbuf_get_from_drawable(pixbuf, pixmap, colormap, 0, 0, 0, 0, width, height);
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      byte[] srcData = new byte[stride * height];
      OS.memmove(srcData, pixels, srcData.length);
      OS.g_object_unref(pixbuf);
      PaletteData palette = new PaletteData(0xff0000, 0xff00, 0xff);
      data = new ImageData(width, height, 24, palette, 4, srcData);
      data.bytesPerLine = stride;
      if (((transparentPixel == (-1)) && (type == SWT.ICON)) && (mask != 0)) {
        int gdkImagePtr = OS.gdk_drawable_get_image(mask, 0, 0, width, height);
        if (gdkImagePtr == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        GdkImage gdkImage = new GdkImage();
        OS.memmove(gdkImage, gdkImagePtr);
        byte[] maskData = new byte[gdkImage.bpl * gdkImage.height];
        OS.memmove(maskData, gdkImage.mem, maskData.length);
        OS.g_object_unref(gdkImagePtr);
        int maskPad;
        for (maskPad = 1; maskPad < 128; maskPad++) {
          int bpl = ((((width + 7) / 8) + (maskPad - 1)) / maskPad) * maskPad;
          if (gdkImage.bpl == bpl) {
            break;
          }
        }
        data.maskPad = 2;
        maskData = ImageData.convertPad(maskData, width, height, 1, maskPad, data.maskPad);
        if (gdkImage.byte_order == OS.GDK_LSB_FIRST) {
          for (int i = 0; i < maskData.length; i++) {
            byte b = maskData[i];
            maskData[i] =
                ((byte)
                    (((((((((b & 0x1) << 7) | ((b & 0x2) << 5)) | ((b & 0x4) << 3))
                                        | ((b & 0x8) << 1))
                                    | ((b & 0x10) >> 1))
                                | ((b & 0x20) >> 3))
                            | ((b & 0x40) >> 5))
                        | ((b & 0x80) >> 7)));
          }
        }
        data.maskData = maskData;
      }
    }
    data.transparentPixel = transparentPixel;
    data.alpha = alpha;
    if ((alpha == (-1)) && (alphaData != null)) {
      data.alphaData = new byte[alphaData.length];
      System.arraycopy(alphaData, 0, data.alphaData, 0, alphaData.length);
    }
    return data;
  }
}
