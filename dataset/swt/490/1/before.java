class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    if (wHint == SWT.DEFAULT) {
      if (columnCount != 0) {
        for (int i = 0; i < columnCount; i++) {
          width += columns[i].getWidth();
        }
      } else {
        int columnWidth = 0;
        GC gc = new GC(this);
        for (int i = 0; i < itemCount; i++) {
          TableItem item = items[i];
          if (item != null) {
            columnWidth = Math.max(columnWidth, item.calculateWidth(0, gc));
          }
        }
        gc.dispose();
        width += columnWidth + getInsetWidth();
      }
    } else {
      width = wHint;
    }
    if (width <= 0) {
      width = DEFAULT_WIDTH;
    }
    int height = 0;
    if (hHint == SWT.DEFAULT) {
      height = (itemCount * getItemHeight()) + getHeaderHeight();
    } else {
      height = hHint;
    }
    if (height <= 0) {
      height = DEFAULT_HEIGHT;
    }
    Rectangle rect = computeTrim(0, 0, width, height);
    return new Point(rect.width, rect.height);
  }
}
