class PlaceHold {
  void sendEvent(EventTable table, Event event) {
    try {
      sendEventCount++;
      if (!filterEvent(event)) {
        if (table != null) {
          sendPreEvent(event);
          try {
            table.sendEvent(event);
          } finally {
            sendPostEvent(event);
          }
        }
      }
    } finally {
      sendEventCount--;
    }
  }
}
