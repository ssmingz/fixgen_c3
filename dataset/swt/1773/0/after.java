class PlaceHold {
  int gtk_leave_notify_event(int widget, int event) {
    display.removeMouseHoverTimeout(handle);
    GdkEventCrossing gdkEvent = new GdkEventCrossing();
    OS.memmove(gdkEvent, event, sizeof);
    if ((gdkEvent.mode != OS.GDK_CROSSING_NORMAL) && (gdkEvent.mode != OS.GDK_CROSSING_UNGRAB)) {
      return 0;
    }
    if ((gdkEvent.state & ((OS.GDK_BUTTON1_MASK | OS.GDK_BUTTON2_MASK) | OS.GDK_BUTTON3_MASK))
        != 0) {
      return 0;
    }
    if (gdkEvent.subwindow != 0) {
      return 0;
    }
    return sendMouseEvent(
            MouseExit, 0, gdkEvent.time, gdkEvent.x_root, gdkEvent.y_root, false, gdkEvent.state)
        ? 0
        : 1;
  }
}
