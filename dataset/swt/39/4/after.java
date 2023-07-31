class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if (defaultCursor != 0) {
      OS.gdk_cursor_unref(defaultCursor);
    }
    defaultCursor = 0;
  }
}
