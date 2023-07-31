class PlaceHold {
  boolean sendKeyEvent(int type, int msg, int wParam, int lParam, Event event) {
    sendEvent(type, event);
    return event.doit;
  }
}
