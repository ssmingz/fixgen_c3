class PlaceHold {
  public void addPropertyListener(int propertyID, OleListener listener) {
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    olePropertyChangeSink.addListener(propertyID, listener);
  }
}
