class PlaceHold {
  void setCursor(int cursor) {
    int window = paintWindow();
    if (window != 0) {
      OS.gdk_window_set_cursor(window, cursor);
      OS.gdk_flush();
    }
  }
}
