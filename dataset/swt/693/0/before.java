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
      event.time = OS.gtk_get_current_event_time();
    }
    eventTable.sendEvent(event);
  }
}
