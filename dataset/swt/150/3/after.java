class PlaceHold {
  int gtk_visibility_notify_event(int widget, int event) {
    if (OS.GTK_VERSION >= OS.VERSION(2, 17, 11)) {
      return 0;
    }
    GdkEventVisibility gdkEvent = new GdkEventVisibility();
    OS.memmove(gdkEvent, event, sizeof);
    int paintWindow = paintWindow();
    int window = gdkEvent.window;
    if (window == paintWindow) {
      if (gdkEvent.state == OS.GDK_VISIBILITY_FULLY_OBSCURED) {
        state |= OBSCURED;
      } else {
        if ((state & OBSCURED) != 0) {
          int[] width = new int[1];
          int[] height = new int[1];
          gdk_window_get_size(window, width, height);
          GdkRectangle rect = new GdkRectangle();
          rect.width = width[0];
          rect.height = height[0];
          OS.gdk_window_invalidate_rect(window, rect, false);
        }
        state &= ~OBSCURED;
      }
    }
    return 0;
  }
}
