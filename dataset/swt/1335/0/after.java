class PlaceHold {
  public void removeListener(int eventType, Listener listener) {
    checkWidget();
    if (listener == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    _removeListener(eventType, listener);
  }
}
