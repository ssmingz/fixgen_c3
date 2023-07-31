class PlaceHold {
  public void removeAccessibleControlListener(AccessibleControlListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    controlListeners.remove(listener);
  }
}
