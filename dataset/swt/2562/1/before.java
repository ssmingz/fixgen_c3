class PlaceHold {
  void sendEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    if (event == null) {
      event = new Event();
    }
    event.display = this;
    event.type = eventType;
    if (event.time == 0) {
      event.time = OS.XtLastTimestampProcessed(xDisplay);
    }
    eventTable.sendEvent(event);
  }
}
