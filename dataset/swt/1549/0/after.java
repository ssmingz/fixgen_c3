class PlaceHold {
  void sendDoubleSelection() {
    NSTableView tableView = ((NSTableView) (view));
    int rowIndex = ((int) (tableView.clickedRow()));
    if (rowIndex == (-1)) {
      rowIndex = ((int) (tableView.selectedRow()));
    }
    if (rowIndex != (-1)) {
      if ((style & SWT.CHECK) != 0) {
        NSArray columns = tableView.tableColumns();
        int columnIndex = ((int) (tableView.clickedColumn()));
        if (columnIndex != (-1)) {
          id column = columns.objectAtIndex(columnIndex);
          if (column.id == checkColumn.id) {
            return;
          }
        }
      }
      Event event = new Event();
      event.item = _getItem(rowIndex);
      sendSelectionEvent(DefaultSelection, event, false);
    }
  }
}
