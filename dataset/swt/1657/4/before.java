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
    Rectangle bounds = getClientArea();
    if ((0 <= x) && (rightX <= bounds.width)) {
      return;
    }
    int absX = 0;
    TreeColumn[] orderedColumns = getOrderedColumns();
    for (int i = 0; i < column.getOrderIndex(); i++) {
      absX += orderedColumns[i].width;
    }
    if (x < bounds.x) {
      horizontalOffset = absX;
    } else {
      horizontalOffset = (absX + column.width) - bounds.width;
    }
    getHorizontalBar().setSelection(horizontalOffset);
    redraw();
    if ((drawCount == 0) && header.isVisible()) {
      header.redraw();
    }
  }
}
