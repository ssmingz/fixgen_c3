class PlaceHold {
  public void setSortColumn(TreeColumn column) {
    checkWidget();
    if ((column != null) && column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column == sortColumn) {
      return;
    }
    sortColumn = column;
    ((NSOutlineView) (view)).setHighlightedTableColumn(column == null ? null : column.nsColumn);
  }
}
