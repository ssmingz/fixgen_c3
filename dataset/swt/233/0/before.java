class PlaceHold {
  void postEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    event.type = eventType;
    event.widget = this;
    Display display = getDisplay();
    if (event.time == 0) {
      event.time = ((int) (OS.GetLastUserEventTime() * 1000.0));
    }
    display.postEvent(event);
  }
}
