class Image {
  public Image(Device device, String filename) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (filename == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    try {
      int length = filename.length();
      char[] chars = new char[length];
      filename.getChars(0, length, chars, 0);
      byte[] buffer = Converter.wcsToMbcs(null, chars, true);
      int pixbuf = OS.gdk_pixbuf_new_from_file(buffer, null);
      if (pixbuf != 0) {
        boolean hasAlpha = OS.gdk_pixbuf_get_has_alpha(pixbuf);
        if (hasAlpha) {
          int width = OS.gdk_pixbuf_get_width(pixbuf);
          int height = OS.gdk_pixbuf_get_height(pixbuf);
          int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
          int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
          byte[] line = new byte[stride];
          alphaData = new byte[width * height];
          for (int y = 0; y < height; y++) {
            OS.memmove(line, pixels + (y * stride), stride);
            for (int x = 0; x < width; x++) {
              alphaData[(y * width) + x] = line[(x * 4) + 3];
              line[(x * 4) + 3] = ((byte) (0xff));
            }
            OS.memmove(pixels + (y * stride), line, stride);
          }
          if (device.useXRender) {
            mask = OS.gdk_pixmap_new(0, width, height, 8);
            if (mask == 0) {
              SWT.error(ERROR_NO_HANDLES);
            }
            GdkImage gdkImage = new GdkImage();
            int imagePtr = OS.gdk_drawable_get_image(mask, 0, 0, width, height);
            if (imagePtr == 0) {
              SWT.error(ERROR_NO_HANDLES);
            }
            OS.memmove(gdkImage, imagePtr);
            if (gdkImage.bpl == width) {
              OS.memmove(gdkImage.mem, alphaData, alphaData.length);
            } else {
              line = new byte[gdkImage.bpl];
              for (int y = 0; y < height; y++) {
                System.arraycopy(alphaData, width * y, line, 0, width);
                OS.memmove(gdkImage.mem + (gdkImage.bpl * y), line, gdkImage.bpl);
              }
            }
            int gc = OS.gdk_gc_new(mask);
            if (gc == 0) {
              SWT.error(ERROR_NO_HANDLES);
            }
            OS.gdk_draw_image(mask, gc, imagePtr, 0, 0, 0, 0, width, height);
            OS.g_object_unref(imagePtr);
            OS.g_object_unref(gc);
          }
        }
        int[] pixmap_return = new int[1];
        OS.gdk_pixbuf_render_pixmap_and_mask(pixbuf, pixmap_return, null, 0);
        this.type = SWT.BITMAP;
        this.pixmap = pixmap_return[0];
        if (pixmap == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.g_object_unref(pixbuf);
        return;
      }
    } catch (SWTException e) {
    }
    init(device, new ImageData(filename));
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
