class PlaceHold {
  void updateColumnWidth(TreeColumn column, int width) {
    int oldWidth = column.width;
    column.width = width;
    Rectangle bounds = getClientArea();
    ScrollBar hBar = getHorizontalBar();
    TreeColumn lastColumn = columns[columns.length - 1];
    hBar.setMaximum(lastColumn.getX() + lastColumn.width);
    hBar.setThumb(bounds.width);
    hBar.setPageIncrement(bounds.width);
    horizontalOffset = hBar.getSelection();
    int x = column.getX();
    redraw(x, 0, bounds.width - x, bounds.height, false);
    if (getHeaderVisible()) {
      header.redraw(x, 0, bounds.width - x, getHeaderHeight(), false);
    }
  }
}
