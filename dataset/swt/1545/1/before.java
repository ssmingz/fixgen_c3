class Image {
  public Image(Device device, Image srcImage, int flag) {
    super(device);
    if (srcImage == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (srcImage.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    switch (flag) {
      case SWT.IMAGE_COPY:
      case SWT.IMAGE_DISABLE:
      case SWT.IMAGE_GRAY:
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    device = this.device;
    this.type = srcImage.type;
    if (OS.USE_CAIRO) {
      if (flag != SWT.IMAGE_DISABLE) {
        transparentPixel = srcImage.transparentPixel;
      }
      alpha = srcImage.alpha;
      if (srcImage.alphaData != null) {
        alphaData = new byte[srcImage.alphaData.length];
        System.arraycopy(srcImage.alphaData, 0, alphaData, 0, alphaData.length);
      }
      int imageSurface = srcImage.surface;
      int width = this.width = srcImage.width;
      int height = this.height = srcImage.height;
      int format =
          (Cairo.cairo_surface_get_content(imageSurface) == Cairo.CAIRO_CONTENT_COLOR)
              ? Cairo.CAIRO_FORMAT_RGB24
              : Cairo.CAIRO_FORMAT_ARGB32;
      boolean hasAlpha = format == Cairo.CAIRO_FORMAT_ARGB32;
      surface = Cairo.cairo_image_surface_create(format, width, height);
      if (surface == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int cairo = Cairo.cairo_create(surface);
      if (cairo == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      Cairo.cairo_set_operator(cairo, CAIRO_OPERATOR_SOURCE);
      Cairo.cairo_set_source_surface(cairo, imageSurface, 0, 0);
      Cairo.cairo_paint(cairo);
      Cairo.cairo_destroy(cairo);
      if (flag != SWT.IMAGE_COPY) {
        int stride = Cairo.cairo_image_surface_get_stride(surface);
        int data = Cairo.cairo_image_surface_get_data(surface);
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
        switch (flag) {
          case SWT.IMAGE_DISABLE:
            {
              Color zeroColor = device.getSystemColor(COLOR_WIDGET_NORMAL_SHADOW);
              RGB zeroRGB = zeroColor.getRGB();
              int zeroRed = zeroRGB.red;
              int zeroGreen = zeroRGB.green;
              int zeroBlue = zeroRGB.blue;
              Color oneColor = device.getSystemColor(COLOR_WIDGET_BACKGROUND);
              RGB oneRGB = oneColor.getRGB();
              int oneRed = oneRGB.red;
              int oneGreen = oneRGB.green;
              int oneBlue = oneRGB.blue;
              byte[] line = new byte[stride];
              for (int y = 0; y < height; y++) {
                OS.memmove(line, data + (y * stride), stride);
                for (int x = 0, offset = 0; x < width; x++, offset += 4) {
                  int a = line[offset + oa] & 0xff;
                  int r = line[offset + or] & 0xff;
                  int g = line[offset + og] & 0xff;
                  int b = line[offset + ob] & 0xff;
                  if (hasAlpha && (a != 0)) {
                    r = ((r * 0xff) + (a / 2)) / a;
                    g = ((g * 0xff) + (a / 2)) / a;
                    b = ((b * 0xff) + (a / 2)) / a;
                  }
                  int intensity = ((r * r) + (g * g)) + (b * b);
                  if (intensity < 98304) {
                    r = zeroRed;
                    g = zeroGreen;
                    b = zeroBlue;
                  } else {
                    r = oneRed;
                    g = oneGreen;
                    b = oneBlue;
                  }
                  if (hasAlpha) {
                    r = (r * a) + 128;
                    r = (r + (r >> 8)) >> 8;
                    g = (g * a) + 128;
                    g = (g + (g >> 8)) >> 8;
                    b = (b * a) + 128;
                    b = (b + (b >> 8)) >> 8;
                  }
                  line[offset + or] = ((byte) (r));
                  line[offset + og] = ((byte) (g));
                  line[offset + ob] = ((byte) (b));
                }
                OS.memmove(data + (y * stride), line, stride);
              }
              break;
            }
          case SWT.IMAGE_GRAY:
            {
              byte[] line = new byte[stride];
              for (int y = 0; y < height; y++) {
                OS.memmove(line, data + (y * stride), stride);
                for (int x = 0, offset = 0; x < width; x++, offset += 4) {
                  int a = line[offset + oa] & 0xff;
                  int r = line[offset + or] & 0xff;
                  int g = line[offset + og] & 0xff;
                  int b = line[offset + ob] & 0xff;
                  if (hasAlpha && (a != 0)) {
                    r = ((r * 0xff) + (a / 2)) / a;
                    g = ((g * 0xff) + (a / 2)) / a;
                    b = ((b * 0xff) + (a / 2)) / a;
                  }
                  int intensity = (((((((r + r) + g) + g) + g) + g) + g) + b) >> 3;
                  if (hasAlpha) {
                    intensity = (intensity * a) + 128;
                    intensity = (intensity + (intensity >> 8)) >> 8;
                  }
                  line[offset + or] = line[offset + og] = line[offset + ob] = ((byte) (intensity));
                }
                OS.memmove(data + (y * stride), line, stride);
              }
              break;
            }
        }
      }
      init();
      return;
    }
    int[] w = new int[1];
    int[] h = new int[1];
    OS.gdk_drawable_get_size(srcImage.pixmap, w, h);
    int width = w[0];
    int height = h[0];
    if (((srcImage.type == SWT.ICON) && (srcImage.mask != 0))
        || (srcImage.transparentPixel != (-1))) {
      if (srcImage.transparentPixel != (-1)) {
        srcImage.createMask();
      }
      int mask = OS.gdk_pixmap_new(0, width, height, 1);
      if (mask == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int gdkGC = OS.gdk_gc_new(mask);
      if (gdkGC == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      OS.gdk_draw_drawable(mask, gdkGC, srcImage.mask, 0, 0, 0, 0, width, height);
      OS.g_object_unref(gdkGC);
      this.mask = mask;
      if ((srcImage.transparentPixel != (-1)) && (srcImage.memGC != null)) {
        srcImage.destroyMask();
      }
    }
    if (flag != SWT.IMAGE_DISABLE) {
      transparentPixel = srcImage.transparentPixel;
    }
    alpha = srcImage.alpha;
    if (srcImage.alphaData != null) {
      alphaData = new byte[srcImage.alphaData.length];
      System.arraycopy(srcImage.alphaData, 0, alphaData, 0, alphaData.length);
    }
    createAlphaMask(width, height);
    int pixmap = OS.gdk_pixmap_new(OS.gdk_get_default_root_window(), width, height, -1);
    if (pixmap == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int gdkGC = OS.gdk_gc_new(pixmap);
    if (gdkGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    this.pixmap = pixmap;
    if (flag == SWT.IMAGE_COPY) {
      OS.gdk_draw_drawable(pixmap, gdkGC, srcImage.pixmap, 0, 0, 0, 0, width, height);
      OS.g_object_unref(gdkGC);
    } else {
      int pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, width, height);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_pixbuf_get_from_drawable(pixbuf, srcImage.pixmap, colormap, 0, 0, 0, 0, width, height);
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      switch (flag) {
        case SWT.IMAGE_DISABLE:
          {
            Color zeroColor = device.getSystemColor(COLOR_WIDGET_NORMAL_SHADOW);
            RGB zeroRGB = zeroColor.getRGB();
            byte zeroRed = ((byte) (zeroRGB.red));
            byte zeroGreen = ((byte) (zeroRGB.green));
            byte zeroBlue = ((byte) (zeroRGB.blue));
            Color oneColor = device.getSystemColor(COLOR_WIDGET_BACKGROUND);
            RGB oneRGB = oneColor.getRGB();
            byte oneRed = ((byte) (oneRGB.red));
            byte oneGreen = ((byte) (oneRGB.green));
            byte oneBlue = ((byte) (oneRGB.blue));
            byte[] line = new byte[stride];
            for (int y = 0; y < height; y++) {
              OS.memmove(line, pixels + (y * stride), stride);
              for (int x = 0; x < width; x++) {
                int offset = x * 3;
                int red = line[offset] & 0xff;
                int green = line[offset + 1] & 0xff;
                int blue = line[offset + 2] & 0xff;
                int intensity = ((red * red) + (green * green)) + (blue * blue);
                if (intensity < 98304) {
                  line[offset] = zeroRed;
                  line[offset + 1] = zeroGreen;
                  line[offset + 2] = zeroBlue;
                } else {
                  line[offset] = oneRed;
                  line[offset + 1] = oneGreen;
                  line[offset + 2] = oneBlue;
                }
              }
              OS.memmove(pixels + (y * stride), line, stride);
            }
            break;
          }
        case SWT.IMAGE_GRAY:
          {
            byte[] line = new byte[stride];
            for (int y = 0; y < height; y++) {
              OS.memmove(line, pixels + (y * stride), stride);
              for (int x = 0; x < width; x++) {
                int offset = x * 3;
                int red = line[offset] & 0xff;
                int green = line[offset + 1] & 0xff;
                int blue = line[offset + 2] & 0xff;
                byte intensity =
                    ((byte)
                        ((((((((red + red) + green) + green) + green) + green) + green) + blue)
                            >> 3));
                line[offset] = line[offset + 1] = line[offset + 2] = intensity;
              }
              OS.memmove(pixels + (y * stride), line, stride);
            }
            break;
          }
      }
      OS.gdk_pixbuf_render_to_drawable(
          pixbuf, pixmap, gdkGC, 0, 0, 0, 0, width, height, GDK_RGB_DITHER_NORMAL, 0, 0);
      OS.g_object_unref(pixbuf);
      OS.g_object_unref(gdkGC);
    }
    init();
  }
}
