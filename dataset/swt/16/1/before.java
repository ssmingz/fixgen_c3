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
      if (OS.IsWinCE) {
        event.time = OS.GetTickCount();
      } else {
        event.time = OS.GetMessageTime();
      }
    }
    eventTable.sendEvent(event);
  }
}
