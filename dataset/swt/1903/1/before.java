class PlaceHold {
  long gdk_pixbuf_get_from_window(
      long dest,
      long src,
      long cmap,
      int src_x,
      int src_y,
      int dest_x,
      int dest_y,
      int width,
      int height) {
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      return OS.gdk_pixbuf_get_from_window(dest, src_x, src_y, width, height);
    } else {
      return OS.gdk_pixbuf_get_from_drawable(
          dest, src, cmap, src_x, src_y, dest_x, dest_y, width, height);
    }
  }
}
