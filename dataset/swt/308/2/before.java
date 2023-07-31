class PlaceHold {
  public void removeSelectionListener(SelectionListener listener) {
    checkWidget();
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    removeListener(Selection, listener);
    removeListener(DefaultSelection, listener);
  }
}
