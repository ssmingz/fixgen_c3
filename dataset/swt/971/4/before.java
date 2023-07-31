class PlaceHold {
  boolean sendKeyEvent(int type, Event event) {
    sendEvent(type, event);
    return event.doit;
  }
}
