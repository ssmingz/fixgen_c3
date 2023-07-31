class PlaceHold {
  public void setSortColumn(TableColumn column) {
    checkWidget();
    if ((column != null) && column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column == sortColumn) {
      return;
    }
    setSort(column, sortDirection);
  }
}
