class PlaceHold {
  void destroy() {
    OS.gdk_cursor_destroy(handle);
    handle = 0;
  }
}
