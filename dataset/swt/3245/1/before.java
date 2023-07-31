class PlaceHold {
  int calculateWidth(int columnIndex, GC gc, boolean callMeasureItem) {
    if ((!callMeasureItem) && (customWidth != (-1))) {
      return customWidth;
    }
    NSBrowserCell cell = parent.dataCell;
    cell.setFont(getFont(columnIndex).handle);
    cell.setTitle(NSString.stringWith(getText(columnIndex)));
    Image image = getImage(columnIndex);
    cell.setImage(image != null ? image.handle : null);
    NSSize size = cell.cellSize();
    int width = ((int) (0.5F + size.width));
    if (callMeasureItem && parent.hooks(MeasureItem)) {
      NSTableView tableView = ((NSTableView) (parent.view));
      int nsColumnIndex = 0;
      if (parent.columnCount > 0) {
        nsColumnIndex = tableView.columnWithIdentifier(parent.columns[columnIndex].nsColumn);
      }
      int rowIndex = parent.indexOf(this);
      NSRect rect = tableView.frameOfCellAtColumn(nsColumnIndex, rowIndex);
      NSRect contentRect = cell.titleRectForBounds(rect);
      int rowHeight = ((int) (tableView.rowHeight()));
      Event event = new Event();
      event.item = this;
      event.index = columnIndex;
      event.gc = gc;
      event.x = ((int) (contentRect.x));
      event.y = ((int) (contentRect.y));
      event.width = width;
      event.height = rowHeight;
      parent.sendEvent(MeasureItem, event);
      if (rowHeight < event.height) {
        tableView.setRowHeight(event.height);
      }
      if (parent.columnCount == 0) {
        int change = event.width - (customWidth != (-1) ? customWidth : width);
        if ((customWidth != (-1)) || (event.width != width)) {
          customWidth = event.width;
        }
        if (change != 0) {
          parent.setScrollWidth(this, false);
        }
      }
      width = event.width;
    }
    return width;
  }
}
