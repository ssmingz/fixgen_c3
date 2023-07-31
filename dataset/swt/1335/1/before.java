class PlaceHold {
  public void addListener(int eventType, Listener listener) {
    checkWidget();
    if (listener == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (eventTable == null) {
      eventTable = new EventTable();
    }
    eventTable.hook(eventType, listener);
  }
}
