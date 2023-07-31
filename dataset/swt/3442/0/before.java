class PlaceHold {
  public void removePropertyListener(int propertyID, OleListener listener) {
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    olePropertyChangeSink.removeListener(propertyID, listener);
  }
}
