class PlaceHold {
  public void setSelection(int row, int column) {
    checkWidget();
    int columnCount = table.getColumnCount();
    int maxColumnIndex = (columnCount == 0) ? 0 : columnCount - 1;
    if ((((row < 0) || (row >= table.getItemCount())) || (column < 0))
        || (column > maxColumnIndex)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setRowColumn(row, column, false);
  }
}
