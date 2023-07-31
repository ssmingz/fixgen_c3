class PlaceHold {
  void sendEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    Display display = getDisplay();
    event.type = eventType;
    event.display = display;
    event.widget = this;
    if (event.time == 0) {
      event.time = ((int) (OS.GetLastUserEventTime() * 1000.0));
    }
    eventTable.sendEvent(event);
  }
}
