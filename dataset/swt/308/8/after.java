class PlaceHold {
  public void addTreeListener(TreeListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    TypedListener typedListener = new TypedListener(listener);
    addListener(Expand, typedListener);
    addListener(Collapse, typedListener);
  }
}
