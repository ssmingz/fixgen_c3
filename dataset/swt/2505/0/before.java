class PlaceHold {
  public void setSelection(int index) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      deselectAll();
    }
    select(index);
    showSelection();
  }
}
