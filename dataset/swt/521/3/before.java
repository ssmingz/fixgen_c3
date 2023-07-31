class PlaceHold {
  public void addAccessibleEditableTextListener(AccessibleEditableTextListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleEditableTextListeners == null) {
      accessibleEditableTextListeners = new Vector<AccessibleEditableTextListener>();
    }
    accessibleEditableTextListeners.addElement(listener);
  }
}
