class PlaceHold {
  public void setSelection(TableItem row, int column) {
    checkWidget();
    int columnCount = table.getColumnCount();
    int maxColumnIndex = (columnCount == 0) ? 0 : columnCount - 1;
    if ((((row == null) || row.isDisposed()) || (column < 0)) || (column > maxColumnIndex)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setRowColumn(table.indexOf(row), column, false);
  }
}
