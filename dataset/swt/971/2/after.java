class PlaceHold {
  boolean sendKeyEvent(int type, int msg, int wParam, int lParam, Event event) {
    sendEvent(type, event);
    if (isDisposed()) {
      return false;
    }
    return event.doit;
  }
}
