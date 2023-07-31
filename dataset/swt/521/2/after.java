class PlaceHold {
  public void addAccessibleActionListener(AccessibleActionListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleActionListeners == null) {
      accessibleActionListeners = new ArrayList<AccessibleActionListener>();
    }
    accessibleActionListeners.add(listener);
  }
}
