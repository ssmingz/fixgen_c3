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
    if (device.useXRender) {
      drawImageXRender(
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
          imgHeight,
          srcImage.mask,
          PictStandardA8);
      return;
    }
    long pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8, srcWidth, srcHeight);
    if (pixbuf == 0) {
      return;
    }
    long colormap = OS.gdk_colormap_get_system();
    gdk_pixbuf_get_from_window(
        pixbuf, srcImage.pixmap, colormap, srcX, srcY, 0, 0, srcWidth, srcHeight);
    int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
    long pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
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
      long scaledPixbuf =
          OS.gdk_pixbuf_scale_simple(pixbuf, destWidth, destHeight, GDK_INTERP_BILINEAR);
      OS.g_object_unref(pixbuf);
      if (scaledPixbuf == 0) {
        return;
      }
      pixbuf = scaledPixbuf;
    }
    OS.gdk_draw_pixbuf(
        data.drawable,
        handle,
        pixbuf,
        0,
        0,
        destX,
        destY,
        destWidth,
        destHeight,
        GDK_RGB_DITHER_NORMAL,
        0,
        0);
    OS.g_object_unref(pixbuf);
  }
}
