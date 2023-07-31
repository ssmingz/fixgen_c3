class PlaceHold {
  public void removeTreeListener(TreeListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    removeListener(Expand, listener);
    removeListener(Collapse, listener);
  }
}
