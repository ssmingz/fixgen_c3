class PlaceHold {
  boolean sendKeyEvent(int type, Event event) {
    sendEvent(type, event);
    if (isDisposed()) {
      return false;
    }
    return event.doit;
  }
}
