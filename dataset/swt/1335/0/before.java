class PlaceHold {
  public void removeListener(int eventType, Listener listener) {
    checkWidget();
    if (listener == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (eventTable == null) {
      return;
    }
    eventTable.unhook(eventType, listener);
  }
}
