class PlaceHold {
  public void addAccessibleTableListener(AccessibleTableListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleTableListeners == null) {
      accessibleTableListeners = new Vector<AccessibleTableListener>();
    }
    accessibleTableListeners.addElement(listener);
  }
}
