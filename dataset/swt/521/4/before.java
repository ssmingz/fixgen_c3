class PlaceHold {
  public void addAccessibleValueListener(AccessibleValueListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleValueListeners == null) {
      accessibleValueListeners = new Vector<AccessibleValueListener>();
    }
    accessibleValueListeners.addElement(listener);
  }
}
