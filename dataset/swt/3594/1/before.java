class PlaceHold {
  void setCursor(long cursor) {
    long window = eventWindow();
    if (window != 0) {
      OS.gdk_window_set_cursor(window, cursor);
      if ((!OS.GDK_WINDOWING_X11()) || OS.GDK_WINDOWING_WAYLAND()) {
        OS.gdk_flush();
      } else {
        long xDisplay = OS.gdk_x11_display_get_xdisplay(OS.gdk_display_get_default());
        OS.XFlush(xDisplay);
      }
    }
  }
}
