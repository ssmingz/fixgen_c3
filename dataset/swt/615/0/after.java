class PlaceHold {
  void setCursor(int cursor) {
    if (enableWindow != 0) {
      OS.gdk_window_set_cursor(enableWindow, cursor);
      if (!OS.GDK_WINDOWING_X11()) {
        OS.gdk_flush();
      } else {
        int xDisplay = OS.GDK_DISPLAY();
        OS.XFlush(xDisplay);
      }
    }
    super.setCursor(cursor);
  }
}
