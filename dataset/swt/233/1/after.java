class PlaceHold {
  void postEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    Display display = getDisplay();
    event.type = eventType;
    event.widget = this;
    event.display = display;
    if (event.time == 0) {
      if (OS.IsWinCE) {
        event.time = OS.GetTickCount();
      } else {
        event.time = OS.GetMessageTime();
      }
    }
    display.postEvent(event);
  }
}
