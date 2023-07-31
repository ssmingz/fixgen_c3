class PlaceHold {
  public void addTreeListener(TreeListener listener) {
    checkWidget();
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    TypedListener typedListener = new TypedListener(listener);
    addListener(Expand, typedListener);
    addListener(Collapse, typedListener);
  }
}
