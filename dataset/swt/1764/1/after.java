class PlaceHold {
  public void setSelection(int[] indices) {
    checkWidget();
    if (indices == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.MULTI) != 0) {
      deselectAll();
    }
    select(indices);
    showSelection();
  }
}
