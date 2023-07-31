class PlaceHold {
  void drawImageAlpha(
      Image srcImage,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      int destX,
      int destY,
      int destWidth,
      int destHeight,
      boolean simple,
      int imgWidth,
      int imgHeight) {
    if (srcImage.alpha == 0) {
      return;
    }
    if (srcImage.alpha == 255) {
      drawImage(
          srcImage,
          srcX,
          srcY,
          srcWidth,
          srcHeight,
          destX,
          destY,
          destWidth,
          destHeight,
          simple,
          imgWidth,
          imgHeight);
      return;
    }
    int pixbuf = OS.gdk_pixbuf_new(OS.GDK_COLORSPACE_RGB(), true, 8, srcWidth, srcHeight);
    if (pixbuf == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int colormap = OS.gdk_colormap_get_system();
    OS.gdk_pixbuf_get_from_drawable(
        pixbuf, srcImage.pixmap, colormap, srcX, srcY, 0, 0, srcWidth, srcHeight);
    int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
    int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
    byte[] line = new byte[stride];
    byte alpha = ((byte) (srcImage.alpha));
    byte[] alphaData = srcImage.alphaData;
    for (int y = 0; y < srcHeight; y++) {
      int alphaIndex = ((y + srcY) * imgWidth) + srcX;
      OS.memmove(line, pixels + (y * stride), stride);
      for (int x = 3; x < stride; x += 4) {
        line[x] = (alphaData == null) ? alpha : alphaData[alphaIndex++];
      }
      OS.memmove(pixels + (y * stride), line, stride);
    }
    if ((srcWidth != destWidth) || (srcHeight != destHeight)) {
      int scaledPixbuf =
          OS.gdk_pixbuf_scale_simple(pixbuf, destWidth, destHeight, GDK_INTERP_BILINEAR);
      OS.g_object_unref(pixbuf);
      if (scaledPixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      pixbuf = scaledPixbuf;
    }
    OS.gdk_pixbuf_render_to_drawable_alpha(
        pixbuf,
        data.drawable,
        0,
        0,
        destX,
        destY,
        destWidth,
        destHeight,
        GDK_PIXBUF_ALPHA_BILEVEL,
        128,
        GDK_RGB_DITHER_NORMAL,
        0,
        0);
    OS.g_object_unref(pixbuf);
  }
}
