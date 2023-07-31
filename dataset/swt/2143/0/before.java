class PlaceHold {
  public void addAccessibleTableCellListener(AccessibleTableCellListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleTableCellListeners == null) {
      accessibleTableCellListeners = new Vector();
    }
    accessibleTableCellListeners.addElement(listener);
  }
}
