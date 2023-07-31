class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    if ((state & WEBKIT_EVENTS_FIX) != 0) {
      return true;
    }
    Event event = new Event();
    if (!setKeyState(event, type, nsEvent)) {
      return true;
    }
    return sendKeyEvent(type, event);
  }
}
