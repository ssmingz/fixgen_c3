class PlaceHold {
  public void removeTreeListener(TreeListener listener) {
    checkWidget();
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    removeListener(Expand, listener);
    removeListener(Collapse, listener);
  }
}
