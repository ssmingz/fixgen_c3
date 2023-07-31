class PlaceHold {
  void setCursor(int cursor) {
    int window = paintWindow();
    if (window != 0) {
      OS.gdk_window_set_cursor(window, cursor);
      if (!OS.GDK_WINDOWING_X11()) {
        OS.gdk_flush();
      } else {
        int xDisplay = OS.GDK_DISPLAY();
        OS.XFlush(xDisplay);
      }
    }
  }
}
