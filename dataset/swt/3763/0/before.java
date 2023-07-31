class PlaceHold {
  String verifyText(String string, int start, int end) {
    if (((string != null) && (string.length() == 0)) && (start == end)) {
      return null;
    }
    Event event = new Event();
    event.text = string;
    event.start = start;
    event.end = end;
    int eventPtr = OS.gtk_get_current_event();
    if (eventPtr != 0) {
      GdkEventKey gdkEvent = new GdkEventKey();
      OS.memmove(gdkEvent, eventPtr, sizeof);
      switch (gdkEvent.type) {
        case OS.GDK_KEY_PRESS:
          setKeyState(event, gdkEvent);
          break;
      }
      OS.gdk_event_free(eventPtr);
    }
    sendEvent(Verify, event);
    if ((!event.doit) || isDisposed()) {
      return null;
    }
    return event.text;
  }
}
