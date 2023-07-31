class PlaceHold {
  public void showColumn(TreeColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column.parent != this) {
      return;
    }
    int x = column.getX();
    int rightX = x + column.width;
    if ((0 <= x) && (rightX <= clientArea.width)) {
      return;
    }
    headerHideToolTip();
    int absX = 0;
    TreeColumn[] orderedColumns = getOrderedColumns();
    for (int i = 0; i < column.getOrderIndex(); i++) {
      absX += orderedColumns[i].width;
    }
    if (x < clientArea.x) {
      horizontalOffset = absX;
    } else {
      horizontalOffset = (absX + column.width) - clientArea.width;
    }
    getHorizontalBar().setSelection(horizontalOffset);
    redraw();
    if ((drawCount <= 0) && header.isVisible()) {
      header.redraw();
    }
  }
}
