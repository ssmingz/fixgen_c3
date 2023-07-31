class PlaceHold {
  public static int createPixbuf(Image image) {
    int pixbuf;
    if (OS.USE_CAIRO) {
      int surface = convertSurface(image);
      int format = Cairo.cairo_image_surface_get_format(surface);
      int width = Cairo.cairo_image_surface_get_width(surface);
      int height = Cairo.cairo_image_surface_get_height(surface);
      boolean hasAlpha = format == Cairo.CAIRO_FORMAT_ARGB32;
      pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, hasAlpha, 8, width, height);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
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
      byte[] line = new byte[stride];
      int surfaceData = Cairo.cairo_image_surface_get_data(surface);
      if (hasAlpha) {
        for (int y = 0; y < height; y++) {
          OS.memmove(line, surfaceData + (y * stride), stride);
          for (int x = 0, offset = 0; x < width; x++, offset += 4) {
            int a = line[offset + oa] & 0xff;
            int r = line[offset + or] & 0xff;
            int g = line[offset + og] & 0xff;
            int b = line[offset + ob] & 0xff;
            line[offset + 3] = ((byte) (a));
            if (a != 0) {
              line[offset + 0] = ((byte) (((r * 0xff) + (a / 2)) / a));
              line[offset + 1] = ((byte) (((g * 0xff) + (a / 2)) / a));
              line[offset + 2] = ((byte) (((b * 0xff) + (a / 2)) / a));
            }
          }
          OS.memmove(pixels + (y * stride), line, stride);
        }
      } else {
        int cairoStride = Cairo.cairo_image_surface_get_stride(surface);
        byte[] cairoLine = new byte[cairoStride];
        for (int y = 0; y < height; y++) {
          OS.memmove(cairoLine, surfaceData + (y * cairoStride), cairoStride);
          for (int x = 0, offset = 0, cairoOffset = 0;
              x < width;
              x++, offset += 3, cairoOffset += 4) {
            byte r = cairoLine[cairoOffset + or];
            byte g = cairoLine[cairoOffset + og];
            byte b = cairoLine[cairoOffset + ob];
            line[offset + 0] = r;
            line[offset + 1] = g;
            line[offset + 2] = b;
          }
          OS.memmove(pixels + (y * stride), line, stride);
        }
      }
      Cairo.cairo_surface_destroy(surface);
    } else {
      int[] w = new int[1];
      int[] h = new int[1];
      if (OS.GTK_VERSION >= OS.VERSION(2, 24, 0)) {
        OS.gdk_pixmap_get_size(image.pixmap, w, h);
      } else {
        OS.gdk_drawable_get_size(image.pixmap, w, h);
      }
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
