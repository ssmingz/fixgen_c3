class PlaceHold {
  void sendEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    Display display = getDisplay();
    event.widget = this;
    event.type = eventType;
    event.display = display;
    if (event.time == 0) {
      event.time = ((int) (System.currentTimeMillis()));
    }
    eventTable.sendEvent(event);
  }
}
