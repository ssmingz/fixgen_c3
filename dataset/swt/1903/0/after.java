class PlaceHold {
  long gdk_pixbuf_get_from_window(
      long dest, long src, int src_x, int src_y, int dest_x, int dest_y, int width, int height) {
    if (OS.GTK3) {
      return OS.gdk_pixbuf_get_from_window(dest, src_x, src_y, width, height);
    } else {
      long cmap = OS.gdk_colormap_get_system();
      return OS.gdk_pixbuf_get_from_drawable(
          dest, src, cmap, src_x, src_y, dest_x, dest_y, width, height);
    }
  }
}
