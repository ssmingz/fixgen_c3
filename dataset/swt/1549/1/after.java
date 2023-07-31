class PlaceHold {
  void sendDoubleSelection() {
    NSOutlineView outlineView = ((NSOutlineView) (view));
    int rowIndex = ((int) (outlineView.clickedRow()));
    if (rowIndex == (-1)) {
      rowIndex = ((int) (outlineView.selectedRow()));
    }
    if (rowIndex != (-1)) {
      if ((style & SWT.CHECK) != 0) {
        NSArray columns = outlineView.tableColumns();
        int columnIndex = ((int) (outlineView.clickedColumn()));
        if (columnIndex != (-1)) {
          id column = columns.objectAtIndex(columnIndex);
          if (column.id == checkColumn.id) {
            return;
          }
        }
      }
      TreeItem item = ((TreeItem) (display.getWidget(outlineView.itemAtRow(rowIndex).id)));
      Event event = new Event();
      event.item = item;
      sendSelectionEvent(DefaultSelection, event, false);
    }
  }
}
