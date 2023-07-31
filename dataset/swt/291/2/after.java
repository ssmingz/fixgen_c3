class PlaceHold {
  int gtk_activate(int widget) {
    sendSelectionEvent(Selection);
    int nextEvent = OS.gdk_event_peek();
    if (nextEvent != 0) {
      int nextEventType = OS.GDK_EVENT_TYPE(nextEvent);
      int currEvent = OS.gtk_get_current_event();
      int currEventType = 0;
      if (currEvent != 0) {
        currEventType = OS.GDK_EVENT_TYPE(currEvent);
        OS.gdk_event_free(currEvent);
      }
      OS.gdk_event_free(nextEvent);
      if ((currEventType == OS.GDK_BUTTON_PRESS) && (nextEventType == OS.GDK_2BUTTON_PRESS)) {
        sendSelectionEvent(DefaultSelection);
      }
    }
    return 0;
  }
}
