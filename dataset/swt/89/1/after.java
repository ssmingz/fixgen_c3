class PlaceHold {
  void sendEvent(EventTable table, Event event) {
    try {
      sendEventCount++;
      if (!filterEvent(event)) {
        if (table != null) {
          int type = event.type;
          sendPreEvent(type);
          try {
            table.sendEvent(event);
          } finally {
            sendPostEvent(type);
          }
        }
      }
    } finally {
      sendEventCount--;
    }
  }
}
