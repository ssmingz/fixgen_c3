class PlaceHold {
  int gtk_leave_notify_event(int widget, int event) {
    display.removeMouseHoverTimeout(handle);
    GdkEventCrossing gdkEvent = new GdkEventCrossing();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.mode != OS.GDK_CROSSING_NORMAL) {
      return 0;
    }
    if (gdkEvent.subwindow != 0) {
      return 0;
    }
    sendMouseEvent(
        MouseExit, 0, gdkEvent.time, gdkEvent.x_root, gdkEvent.y_root, gdkEvent.state, event);
    return 0;
  }
}
