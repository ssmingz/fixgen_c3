class PlaceHold {
  void setCursor(int cursor) {
    if (enableWindow != 0) {
      OS.gdk_window_set_cursor(enableWindow, cursor);
      OS.gdk_flush();
    }
    super.setCursor(cursor);
  }
}
