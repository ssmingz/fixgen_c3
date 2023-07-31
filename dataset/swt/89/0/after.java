class PlaceHold {
  void sendEvent(EventTable eventTable, Event event) {
    int type = event.type;
    sendPreEvent(type);
    try {
      eventTable.sendEvent(event);
    } finally {
      sendPostEvent(type);
    }
  }
}
