class PlaceHold {
  void sendScrollEvent(int detail) {
    Event event = new Event();
    event.detail = detail;
    sendEvent(Selection, event);
  }
}
