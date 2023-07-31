class PlaceHold {
  void updateColumnWidth(TreeColumn column, int width) {
    column.width = width;
    Rectangle bounds = getClientArea();
    int maximum = 0;
    for (int i = 0; i < columns.length; i++) {
      maximum += columns[i].width;
    }
    ScrollBar hBar = getHorizontalBar();
    hBar.setMaximum(maximum);
    if (hBar.getThumb() != bounds.width) {
      hBar.setThumb(bounds.width);
      hBar.setPageIncrement(bounds.width);
    }
    hBar.setVisible(bounds.width < maximum);
    boolean offsetChanged = false;
    int selection = hBar.getSelection();
    if (selection != horizontalOffset) {
      horizontalOffset = selection;
      offsetChanged = true;
    }
    GC gc = new GC(this);
    column.computeDisplayText(gc);
    for (int i = 0; i < items.length; i++) {
      items[i].updateColumnWidth(column, gc);
    }
    gc.dispose();
    int x = 0;
    if (!offsetChanged) {
      x = column.getX();
    }
    redraw(x, 0, bounds.width - x, bounds.height, false);
    if ((drawCount == 0) && header.getVisible()) {
      header.redraw(x, 0, bounds.width - x, getHeaderHeight(), false);
    }
    column.sendEvent(Resize);
    TreeColumn[] orderedColumns = getOrderedColumns();
    for (int i = column.getOrderIndex() + 1; i < orderedColumns.length; i++) {
      if (!orderedColumns[i].isDisposed()) {
        orderedColumns[i].sendEvent(Move);
      }
    }
  }
}
