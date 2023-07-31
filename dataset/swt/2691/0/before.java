class PlaceHold {
  void redraw(int columnIndex) {
    if ((parent.currentItem == this) || (!isDrawing())) {
      return;
    }
    NSTableView tableView = ((NSTableView) (parent.view));
    NSRect rect = null;
    if ((((columnIndex == (-1)) || parent.hooks(MeasureItem)) || parent.hooks(EraseItem))
        || parent.hooks(PaintItem)) {
      rect = tableView.rectOfRow(parent.indexOf(this));
    } else {
      int index;
      if (parent.columnCount == 0) {
        index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
      } else if ((0 <= columnIndex) && (columnIndex < parent.columnCount)) {
        index = ((int) (tableView.columnWithIdentifier(parent.columns[columnIndex].nsColumn)));
      } else {
        return;
      }
      rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    }
    tableView.setNeedsDisplayInRect(rect);
  }
}
