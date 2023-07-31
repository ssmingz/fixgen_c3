class PlaceHold {
  public void removeSelectionListener(SelectionListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    removeListener(Selection, listener);
    removeListener(DefaultSelection, listener);
  }
}
