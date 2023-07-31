class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      deselectAll();
    }
    select(start, end);
    showSelection();
    if ((style & SWT.MULTI) != 0) {
      if ((0 <= start) && (start <= end)) {
        setFocusIndex(start);
      }
    }
  }
}
