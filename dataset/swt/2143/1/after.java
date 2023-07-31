class PlaceHold {
  public void addAccessibleListener(AccessibleListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleListeners == null) {
      accessibleListeners = new Vector<AccessibleListener>();
    }
    accessibleListeners.add(listener);
  }
}
