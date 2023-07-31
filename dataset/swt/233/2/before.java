class PlaceHold {
  void postEvent(int eventType, Event event) {
    if (eventTable == null) {
      return;
    }
    event.type = eventType;
    event.widget = this;
    Display display = getDisplay();
    if (event.time == 0) {
      event.time = OS.XtLastTimestampProcessed(display.xDisplay);
    }
    display.postEvent(event);
  }
}
