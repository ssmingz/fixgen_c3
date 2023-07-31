class PlaceHold {
  public void addAccessibleAttributeListener(AccessibleAttributeListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleAttributeListeners == null) {
      accessibleAttributeListeners = new Vector();
    }
    accessibleAttributeListeners.addElement(listener);
  }
}
