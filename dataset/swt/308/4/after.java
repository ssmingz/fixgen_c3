class PlaceHold {
  public void addPropertyListener(int propertyID, OleListener listener) {
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    olePropertyChangeSink.addListener(propertyID, listener);
  }
}
