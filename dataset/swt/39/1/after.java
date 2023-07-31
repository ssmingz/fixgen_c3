class PlaceHold {
  void destroy() {
    OS.gdk_cursor_unref(handle);
    handle = 0;
  }
}
