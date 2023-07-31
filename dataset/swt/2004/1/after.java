class PlaceHold {
  void postEvent(int eventType, Event event) {
    sendEvent(eventType, event, false);
  }
}
