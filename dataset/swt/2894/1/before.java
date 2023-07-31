class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      deselectAll();
    }
    select(start, end);
    showSelection();
  }
}
