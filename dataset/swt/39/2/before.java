class PlaceHold {
  void setCursor(int cursor) {
    int defaultCursor = 0;
    if (cursor == 0) {
      defaultCursor = OS.gdk_cursor_new(GDK_XTERM);
    }
    super.setCursor(cursor != 0 ? cursor : defaultCursor);
    if (cursor == 0) {
      OS.gdk_cursor_destroy(defaultCursor);
    }
  }
}
