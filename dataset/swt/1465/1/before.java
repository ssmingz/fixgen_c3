class PlaceHold {
  int calculateWidth(int columnIndex, GC gc, boolean recurse, boolean callMeasureItem) {
    if (!cached) {
      return 0;
    }
    int width = 0;
    if ((!callMeasureItem) && (customWidth != (-1))) {
      width = customWidth;
    } else {
      NSBrowserCell cell = parent.dataCell;
      cell.setFont(getFont(columnIndex).handle);
      cell.setTitle(NSString.stringWith(getText(columnIndex)));
      Image image = getImage(columnIndex);
      cell.setImage(image != null ? image.handle : null);
      NSSize size = cell.cellSize();
      width = ((int) (Math.ceil(size.width)));
      if (callMeasureItem && parent.hooks(MeasureItem)) {
        NSOutlineView outlineView = ((NSOutlineView) (parent.view));
        int nsColumnIndex = 0;
        if (parent.columnCount > 0) {
          nsColumnIndex = outlineView.columnWithIdentifier(parent.columns[columnIndex].nsColumn);
        }
        int rowIndex = ((int) (outlineView.rowForItem(handle)));
        NSRect rect = outlineView.frameOfCellAtColumn(nsColumnIndex, rowIndex);
        NSRect contentRect = cell.titleRectForBounds(rect);
        int rowHeight = ((int) (outlineView.rowHeight()));
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
          outlineView.setRowHeight(event.height);
        }
        if (parent.columnCount == 0) {
          int change = event.width - (customWidth != (-1) ? customWidth : width);
          if ((customWidth != (-1)) || (event.width != width)) {
            customWidth = event.width;
          }
          if (change != 0) {
            parent.setScrollWidth(this, false, false);
          }
        }
        width = event.width;
      }
    }
    if (columnIndex == 0) {
      NSTableColumn column =
          (parent.columnCount == 0) ? parent.firstColumn : parent.columns[0].nsColumn;
      NSOutlineView outlineView = ((NSOutlineView) (parent.view));
      int rowIndex = ((int) (outlineView.rowForItem(handle)));
      int nsColumnIndex = ((int) (outlineView.columnWithIdentifier(column)));
      NSRect columnRect = outlineView.rectOfColumn(nsColumnIndex);
      NSRect cellRect = outlineView.frameOfCellAtColumn(nsColumnIndex, rowIndex);
      width += cellRect.x - columnRect.x;
    }
    if (recurse && expanded) {
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        if ((item != null) && (!item.isDisposed())) {
          width = Math.max(width, item.calculateWidth(columnIndex, gc, recurse, callMeasureItem));
        }
      }
    }
    return width;
  }
}
