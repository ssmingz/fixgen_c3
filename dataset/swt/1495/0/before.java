class PlaceHold {
  public void setSelection(int row, int column) {
    checkWidget();
    if ((((row < 0) || (row >= table.getItemCount())) || (column < 0))
        || (column >= table.getColumnCount())) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setRowColumn(row, column, false);
  }
}
