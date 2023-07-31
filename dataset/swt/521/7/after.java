class PlaceHold {
  public void addAccessibleControlListener(AccessibleControlListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleControlListeners == null) {
      accessibleControlListeners = new ArrayList<AccessibleControlListener>();
    }
    accessibleControlListeners.add(listener);
  }
}
