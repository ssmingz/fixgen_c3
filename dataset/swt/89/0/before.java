class PlaceHold {
  void sendEvent(EventTable eventTable, Event event) {
    sendPreEvent(event);
    try {
      eventTable.sendEvent(event);
    } finally {
      sendPostEvent(event);
    }
  }
}
