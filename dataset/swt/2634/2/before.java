class PlaceHold {
  int gtk_enter_notify_event(int widget, int event) {
    GdkEventCrossing gdkEvent = new GdkEventCrossing();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.mode != OS.GDK_CROSSING_NORMAL) {
      return 0;
    }
    if (gdkEvent.subwindow != 0) {
      return 0;
    }
    sendMouseEvent(MouseEnter, 0, event);
    return 0;
  }
}
