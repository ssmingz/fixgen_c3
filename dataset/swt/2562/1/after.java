class PlaceHold {
  void sendEvent(int eventType, Event event) {
    if ((eventTable == null) && (filterTable == null)) {
      return;
    }
    if (event == null) {
      event = new Event();
    }
    event.display = this;
    event.type = eventType;
    if (event.time == 0) {
      event.time = getLastEventTime();
    }
    if (!filterEvent(event)) {
      if (eventTable != null) {
        eventTable.sendEvent(event);
      }
    }
  }
}
