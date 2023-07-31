class PlaceHold {
  void drawImage(
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
    if ((srcWidth == destWidth) && (srcHeight == destHeight)) {
      OS.gdk_draw_drawable(
          data.drawable, handle, srcImage.pixmap, srcX, srcY, destX, destY, destWidth, destHeight);
    } else {
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
            0,
            -1);
        return;
      }
      int pixbuf = scale(srcImage.pixmap, srcX, srcY, srcWidth, srcHeight, destWidth, destHeight);
      if (pixbuf != 0) {
        OS.gdk_pixbuf_render_to_drawable(
            pixbuf,
            data.drawable,
            handle,
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
  }
}
