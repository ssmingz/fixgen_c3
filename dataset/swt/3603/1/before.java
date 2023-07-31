class PlaceHold {
  void redraw(int columnIndex) {
    NSTableView tableView = ((NSTableView) (parent.view));
    NSRect rect = null;
    if ((((columnIndex == (-1)) || parent.hooks(MeasureItem)) || parent.hooks(EraseItem))
        || parent.hooks(PaintItem)) {
      rect = tableView.rectOfRow(parent.indexOf(this));
    } else {
      int index;
      if (parent.columnCount == 0) {
        index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
      } else {
        TableColumn column = parent.getColumn(columnIndex);
        index = ((int) (tableView.columnWithIdentifier(column.nsColumn)));
      }
      rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    }
    tableView.setNeedsDisplayInRect(rect);
  }
}
