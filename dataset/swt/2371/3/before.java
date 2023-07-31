class PlaceHold {
  int calculateWidth(int columnIndex, GC gc, boolean recurse, boolean callMeasureItem) {
    int width = 0;
    if ((!callMeasureItem) && (customWidth != (-1))) {
      width = customWidth;
    } else {
      NSBrowserCell cell = parent.dataCell;
      cell.setFont(getFont(columnIndex).handle);
      cell.setTitle(NSString.stringWith(getText(columnIndex)));
      Image image = getImage(columnIndex);
      cell.setImage(image != null ? image.handle : null);
      NSRect rect = new NSRect();
      rect.width = rect.height = Float.MAX_VALUE;
      NSSize size = cell.cellSizeForBounds(rect);
      width = ((int) (size.width));
      if (callMeasureItem && parent.hooks(MeasureItem)) {
        NSOutlineView outlineView = ((NSOutlineView) (parent.view));
        int nsColumnIndex = 0;
        if (parent.columnCount > 0) {
          nsColumnIndex = outlineView.columnWithIdentifier(parent.columns[columnIndex].nsColumn);
        }
        int rowIndex = ((int) (outlineView.rowForItem(handle)));
        rect = outlineView.frameOfCellAtColumn(nsColumnIndex, rowIndex);
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
            parent.setScrollWidth(this);
          }
        }
        width = event.width;
      }
    }
    if (recurse && expanded) {
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        if (((item != null) && (!item.isDisposed())) && item.cached) {
          width = Math.max(width, item.calculateWidth(columnIndex, gc, recurse, callMeasureItem));
        }
      }
    }
    return width;
  }
}
