class PlaceHold {
  public void setSelection(TableItem row, int column) {
    checkWidget();
    if ((((row == null) || row.isDisposed()) || (column < 0))
        || (column >= table.getColumnCount())) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setRowColumn(table.indexOf(row), column, false);
  }
}
