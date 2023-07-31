class PlaceHold {
  public void removeAccessibleTextListener(AccessibleTextListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    textListeners.remove(listener);
  }
}
