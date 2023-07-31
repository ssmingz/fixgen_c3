class PlaceHold {
  public void setSortColumn(TableColumn column) {
    checkWidget();
    if ((column != null) && column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column == sortColumn) {
      return;
    }
    sortColumn = column;
    ((NSTableView) (view)).setHighlightedTableColumn(column == null ? null : column.nsColumn);
  }
}
