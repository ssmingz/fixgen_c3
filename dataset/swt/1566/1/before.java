class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    } else if (columns.length == 0) {
      for (int i = 0; i < itemsCount; i++) {
        Rectangle itemBounds = items[i].getBounds();
        width = Math.max(width, itemBounds.x + itemBounds.width);
      }
    } else {
      TableColumn[] orderedColumns = getOrderedColumns();
      TableColumn lastColumn = orderedColumns[orderedColumns.length - 1];
      width = lastColumn.getX() + lastColumn.width;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    } else {
      height = getHeaderHeight() + (itemsCount * itemHeight);
    }
    Rectangle result = computeTrim(0, 0, width, height);
    return new Point(result.width, result.height);
  }
}
