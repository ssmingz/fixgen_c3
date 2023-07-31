class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    if (wHint == SWT.DEFAULT) {
      GC gc = new GC(this);
      int columnCount = Math.max(this.columnCount, 1);
      for (int j = 0; j < columnCount; j++) {
        int columnWidth = 0;
        for (int i = 0; i < itemCount; i++) {
          TableItem item = items[i];
          columnWidth = Math.max(columnWidth, item.calculateWidth(j, gc));
        }
        width += columnWidth + EXTRA_WIDTH;
      }
      gc.dispose();
      if ((style & SWT.CHECK) != 0) {
        width += CHECK_COLUMN_WIDTH + EXTRA_WIDTH;
      }
    } else {
      width = wHint;
    }
    if (width <= 0) {
      width = DEFAULT_WIDTH;
    }
    int height = 0;
    if (hHint == SWT.DEFAULT) {
      height = itemCount * getItemHeight();
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
