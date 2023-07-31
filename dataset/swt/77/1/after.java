class PlaceHold {
  void createSurface() {
    if (surface != 0) {
      return;
    }
    if (transparentPixel != (-1)) {
      createMask();
    }
    int[] w = new int[1];
    int[] h = new int[1];
    OS.gdk_drawable_get_size(pixmap, w, h);
    int width = w[0];
    int height = h[0];
    if (((mask != 0) || (alpha != (-1))) || (alphaData != null)) {
      int pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8, width, height);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_pixbuf_get_from_drawable(pixbuf, pixmap, colormap, 0, 0, 0, 0, width, height);
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      byte[] line = new byte[stride];
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
      if ((mask != 0) && (OS.gdk_drawable_get_depth(mask) == 1)) {
        int maskPixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, width, height);
        if (maskPixbuf == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.gdk_pixbuf_get_from_drawable(maskPixbuf, mask, 0, 0, 0, 0, 0, width, height);
        int maskStride = OS.gdk_pixbuf_get_rowstride(maskPixbuf);
        int maskPixels = OS.gdk_pixbuf_get_pixels(maskPixbuf);
        byte[] maskLine = new byte[maskStride];
        int offset = pixels;
        int maskOffset = maskPixels;
        for (int y = 0; y < height; y++) {
          OS.memmove(line, offset, stride);
          OS.memmove(maskLine, maskOffset, maskStride);
          for (int x = 0, offset1 = 0; x < width; x++, offset1 += 4) {
            if (maskLine[x * 3] == 0) {
              line[offset1 + 0] = line[offset1 + 1] = line[offset1 + 2] = line[offset1 + 3] = 0;
            } else {
              byte r = line[offset1 + 0];
              byte g = line[offset1 + 1];
              byte b = line[offset1 + 2];
              line[offset1 + oa] = ((byte) (0xff));
              line[offset1 + or] = r;
              line[offset1 + og] = g;
              line[offset1 + ob] = b;
            }
          }
          OS.memmove(offset, line, stride);
          offset += stride;
          maskOffset += maskStride;
        }
        OS.g_object_unref(maskPixbuf);
      } else if (alpha != (-1)) {
        int offset = pixels;
        for (int y = 0; y < height; y++) {
          OS.memmove(line, offset, stride);
          for (int x = 0, offset1 = 0; x < width; x++, offset1 += 4) {
            int r = ((line[offset1 + 0] & 0xff) * alpha) + 128;
            r = (r + (r >> 8)) >> 8;
            int g = ((line[offset1 + 1] & 0xff) * alpha) + 128;
            g = (g + (g >> 8)) >> 8;
            int b = ((line[offset1 + 2] & 0xff) * alpha) + 128;
            b = (b + (b >> 8)) >> 8;
            line[offset1 + oa] = ((byte) (alpha));
            line[offset1 + or] = ((byte) (r));
            line[offset1 + og] = ((byte) (g));
            line[offset1 + ob] = ((byte) (b));
          }
          OS.memmove(offset, line, stride);
          offset += stride;
        }
      } else if (alphaData != null) {
        int offset = pixels;
        for (int y = 0; y < h[0]; y++) {
          OS.memmove(line, offset, stride);
          for (int x = 0, offset1 = 0; x < width; x++, offset1 += 4) {
            int alpha = alphaData[(y * w[0]) + x] & 0xff;
            int r = ((line[offset1 + 0] & 0xff) * alpha) + 128;
            r = (r + (r >> 8)) >> 8;
            int g = ((line[offset1 + 1] & 0xff) * alpha) + 128;
            g = (g + (g >> 8)) >> 8;
            int b = ((line[offset1 + 2] & 0xff) * alpha) + 128;
            b = (b + (b >> 8)) >> 8;
            line[offset1 + oa] = ((byte) (alpha));
            line[offset1 + or] = ((byte) (r));
            line[offset1 + og] = ((byte) (g));
            line[offset1 + ob] = ((byte) (b));
          }
          OS.memmove(offset, line, stride);
          offset += stride;
        }
      } else {
        int offset = pixels;
        for (int y = 0; y < h[0]; y++) {
          OS.memmove(line, offset, stride);
          for (int x = 0, offset1 = 0; x < width; x++, offset1 += 4) {
            byte r = line[offset1 + 0];
            byte g = line[offset1 + 1];
            byte b = line[offset1 + 2];
            line[offset1 + oa] = ((byte) (0xff));
            line[offset1 + or] = r;
            line[offset1 + og] = g;
            line[offset1 + ob] = b;
          }
          OS.memmove(offset, line, stride);
          offset += stride;
        }
      }
      surfaceData = OS.g_malloc(stride * height);
      OS.memmove(surfaceData, pixels, stride * height);
      surface =
          Cairo.cairo_image_surface_create_for_data(
              surfaceData, CAIRO_FORMAT_ARGB32, width, height, stride);
      OS.g_object_unref(pixbuf);
    } else {
      int xDisplay = OS.GDK_DISPLAY();
      int xDrawable = OS.GDK_PIXMAP_XID(pixmap);
      int xVisual = OS.gdk_x11_visual_get_xvisual(OS.gdk_visual_get_system());
      surface = Cairo.cairo_xlib_surface_create(xDisplay, xDrawable, xVisual, width, height);
    }
    if ((transparentPixel != (-1)) && (memGC != null)) {
      destroyMask();
    }
  }
}
