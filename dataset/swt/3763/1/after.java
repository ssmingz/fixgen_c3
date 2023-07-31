class PlaceHold {
  void sendSelectionEvent(int eventType, Event event, boolean send) {
    if ((eventTable == null) && (!display.filters(eventType))) {
      return;
    }
    if (event == null) {
      event = new Event();
    }
    long ptr = OS.gtk_get_current_event();
    if (ptr != 0) {
      GdkEvent gdkEvent = new GdkEvent();
      OS.memmove(gdkEvent, ptr, sizeof);
      switch (gdkEvent.type) {
        case OS.GDK_KEY_PRESS:
        case OS.GDK_KEY_RELEASE:
        case OS.GDK_BUTTON_PRESS:
        case OS.GDK_2BUTTON_PRESS:
        case OS.GDK_BUTTON_RELEASE:
          {
            int[] state = new int[1];
            OS.gdk_event_get_state(ptr, state);
            setInputState(event, state[0]);
            break;
          }
      }
      OS.gdk_event_free(ptr);
    }
    sendEvent(eventType, event, send);
  }
}
