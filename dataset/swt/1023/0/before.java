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
      event.time = OS.XtLastTimestampProcessed(display.xDisplay);
    }
    display.postEvent(event);
  }
}
