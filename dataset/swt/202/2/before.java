class PlaceHold {
  public void removeAccessibleListener(AccessibleListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    accessibleListeners.remove(listener);
  }
}
