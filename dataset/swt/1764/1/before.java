class PlaceHold {
  public void setSelection(int[] indices) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      deselectAll();
    }
    select(indices);
    showSelection();
  }
}
