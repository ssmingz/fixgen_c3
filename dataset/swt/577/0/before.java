class PlaceHold {
  void createFromPixbuf(int type, int pixbuf) {
    this.type = type;
    boolean hasAlpha = OS.gdk_pixbuf_get_has_alpha(pixbuf);
    if (OS.USE_CAIRO_SURFACE) {
      int width = this.width = OS.gdk_pixbuf_get_width(pixbuf);
      int height = this.height = OS.gdk_pixbuf_get_height(pixbuf);
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      int format = (hasAlpha) ? Cairo.CAIRO_FORMAT_ARGB32 : Cairo.CAIRO_FORMAT_RGB24;
      int cairoStride = Cairo.cairo_format_stride_for_width(format, width);
      int data = surfaceData = OS.g_malloc(cairoStride * height);
      if (surfaceData == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      surface =
          Cairo.cairo_image_surface_create_for_data(
              surfaceData, format, width, height, cairoStride);
      if (surface == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      byte[] line = new byte[stride];
      int ptr = OS.malloc(4);
      OS.memmove(ptr, new int[] {1}, 4);
      OS.memmove(line, ptr, 1);
      OS.free(ptr);
      boolean bigendian = line[0] == 0;
      int oa = 0;
      int or = 0;
      int og = 0;
      int ob = 0;
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
      if (hasAlpha) {
        byte[] cairoLine = new byte[cairoStride];
        alphaData = new byte[width * height];
        for (int y = 0, alphaOffset = 0; y < height; y++) {
          OS.memmove(line, pixels + (y * stride), stride);
          for (int x = 0, offset = 0; x < width; x++, offset += 4) {
            int a = line[offset + 3] & 0xff;
            int r = ((line[offset + 0] & 0xff) * a) + 128;
            r = (r + (r >> 8)) >> 8;
            int g = ((line[offset + 1] & 0xff) * a) + 128;
            g = (g + (g >> 8)) >> 8;
            int b = ((line[offset + 2] & 0xff) * a) + 128;
            b = (b + (b >> 8)) >> 8;
            cairoLine[offset + oa] = ((byte) (a));
            cairoLine[offset + or] = ((byte) (r));
            cairoLine[offset + og] = ((byte) (g));
            cairoLine[offset + ob] = ((byte) (b));
            alphaData[alphaOffset++] = ((byte) (a));
          }
          OS.memmove(data + (y * cairoStride), cairoLine, cairoStride);
        }
      } else {
        byte[] cairoLine = new byte[cairoStride];
        for (int y = 0; y < height; y++) {
          OS.memmove(line, pixels + (y * stride), stride);
          for (int x = 0, offset = 0, cairoOffset = 0;
              x < width;
              x++, offset += 3, cairoOffset += 4) {
            int r = line[offset + 0] & 0xff;
            int g = line[offset + 1] & 0xff;
            int b = line[offset + 2] & 0xff;
            cairoLine[cairoOffset + or] = ((byte) (r));
            cairoLine[cairoOffset + og] = ((byte) (g));
            cairoLine[cairoOffset + ob] = ((byte) (b));
          }
          OS.memmove(data + (y * cairoStride), cairoLine, cairoStride);
        }
      }
    } else {
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
        createAlphaMask(width, height);
      }
      int[] pixmap_return = new int[1];
      OS.gdk_pixbuf_render_pixmap_and_mask(pixbuf, pixmap_return, null, 0);
      this.pixmap = pixmap_return[0];
      if (pixmap == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
    }
  }
}
