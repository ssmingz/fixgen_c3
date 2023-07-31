class PlaceHold {
  long scale(
      long src, int srcX, int srcY, int srcWidth, int srcHeight, int destWidth, int destHeight) {
    long pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, false, 8, srcWidth, srcHeight);
    if (pixbuf == 0) {
      return 0;
    }
    gdk_pixbuf_get_from_window(pixbuf, src, srcX, srcY, 0, 0, srcWidth, srcHeight);
    long scaledPixbuf =
        OS.gdk_pixbuf_scale_simple(pixbuf, destWidth, destHeight, GDK_INTERP_BILINEAR);
    OS.g_object_unref(pixbuf);
    return scaledPixbuf;
  }
}
