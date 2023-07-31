class PlaceHold {
  public void removePropertyListener(int propertyID, OleListener listener) {
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    olePropertyChangeSink.removeListener(propertyID, listener);
  }
}
