class PlaceHold {
  void postEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    event.type = eventType;
    event.widget = this;
    if (event.time == 0) {
      if (OS.IsWinCE) {
        event.time = OS.GetTickCount();
      } else {
        event.time = OS.GetMessageTime();
      }
    }
    Display display = getDisplay();
    display.postEvent(event);
  }
}
