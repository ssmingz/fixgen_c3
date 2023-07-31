class PlaceHold {
  public void clear(int index, boolean all) {
    checkWidget();
    if ((index < 0) || (index >= itemCount)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    parent.clear(handle, index, all);
  }
}
