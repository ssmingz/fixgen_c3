class PlaceHold {
  int gtk_motion_notify_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.window != OS.gtk_tree_view_get_bin_window(handle)) {
      return 0;
    }
    int border = getBorderWidth();
    int headerHeight = getHeaderHeight();
    gdkEvent.x += border;
    gdkEvent.y += headerHeight;
    OS.memmove(event, gdkEvent, sizeof);
    int result = super.gtk_motion_notify_event(widget, event);
    gdkEvent.x -= border;
    gdkEvent.y -= headerHeight;
    OS.memmove(event, gdkEvent, sizeof);
    return result;
  }
}
