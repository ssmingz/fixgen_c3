class PlaceHold {
  public static int createPixbuf(Image image) {
    int pixbuf;
    if (OS.USE_CAIRO_SURFACE) {
      Rectangle bounds = image.getBounds();
      int w = bounds.width;
      int h = bounds.height;
      pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8, w, h);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      byte[] line = new byte[stride];
      int ptr = OS.malloc(4);
      OS.memmove(ptr, new int[] {1}, 4);
      OS.memmove(line, ptr, 1);
      OS.free(ptr);
      int oa;
      int or;
      int og;
      int ob;
      boolean bigendian = line[0] == 0;
      if (bigendian) {
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
      int surfaceData = image.surfaceData;
      for (int y = 0; y < h; y++) {
        OS.memmove(line, surfaceData + (y * stride), stride);
        for (int x = 0, offset = 0; x < w; x++, offset += 4) {
          int a = line[offset + oa] & 0xff;
          int r = line[offset + or] & 0xff;
          int g = line[offset + og] & 0xff;
          int b = line[offset + ob] & 0xff;
          line[offset + 3] = ((byte) (a));
          if (a != 0) {
            line[offset + 0] = ((byte) ((r / ((float) (a))) * 0xff));
            line[offset + 1] = ((byte) ((g / ((float) (a))) * 0xff));
            line[offset + 2] = ((byte) ((b / ((float) (a))) * 0xff));
          }
        }
        OS.memmove(pixels + (y * stride), line, stride);
      }
    } else {
      int[] w = new int[1];
      int[] h = new int[1];
      OS.gdk_drawable_get_size(image.pixmap, w, h);
      int colormap = OS.gdk_colormap_get_system();
      boolean hasMask = (image.mask != 0) && (OS.gdk_drawable_get_depth(image.mask) == 1);
      if (hasMask) {
        pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8, w[0], h[0]);
        if (pixbuf == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.gdk_pixbuf_get_from_drawable(pixbuf, image.pixmap, colormap, 0, 0, 0, 0, w[0], h[0]);
        int maskPixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, w[0], h[0]);
        if (maskPixbuf == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.gdk_pixbuf_get_from_drawable(maskPixbuf, image.mask, 0, 0, 0, 0, 0, w[0], h[0]);
        int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
        int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
        byte[] line = new byte[stride];
        int maskStride = OS.gdk_pixbuf_get_rowstride(maskPixbuf);
        int maskPixels = OS.gdk_pixbuf_get_pixels(maskPixbuf);
        byte[] maskLine = new byte[maskStride];
        for (int y = 0; y < h[0]; y++) {
          int offset = pixels + (y * stride);
          OS.memmove(line, offset, stride);
          int maskOffset = maskPixels + (y * maskStride);
          OS.memmove(maskLine, maskOffset, maskStride);
          for (int x = 0; x < w[0]; x++) {
            if (maskLine[x * 3] == 0) {
              line[(x * 4) + 3] = 0;
            }
          }
          OS.memmove(offset, line, stride);
        }
        OS.g_object_unref(maskPixbuf);
      } else {
        ImageData data = image.getImageData();
        boolean hasAlpha = data.getTransparencyType() == SWT.TRANSPARENCY_ALPHA;
        pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, hasAlpha, 8, w[0], h[0]);
        if (pixbuf == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.gdk_pixbuf_get_from_drawable(pixbuf, image.pixmap, colormap, 0, 0, 0, 0, w[0], h[0]);
        if (hasAlpha) {
          byte[] alpha = data.alphaData;
          int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
          int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
          byte[] line = new byte[stride];
          for (int y = 0; y < h[0]; y++) {
            int offset = pixels + (y * stride);
            OS.memmove(line, offset, stride);
            for (int x = 0; x < w[0]; x++) {
              line[(x * 4) + 3] = alpha[(y * w[0]) + x];
            }
            OS.memmove(offset, line, stride);
          }
        }
      }
    }
    return pixbuf;
  }
}
