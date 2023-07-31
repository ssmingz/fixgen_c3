class PlaceHold {
  public void addSelectionListener(SelectionListener listener) {
    checkWidget();
    if (listener == null) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    TypedListener typedListener = new TypedListener(listener);
    addListener(Selection, typedListener);
    addListener(DefaultSelection, typedListener);
  }
}
