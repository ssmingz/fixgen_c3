class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if (wHint == SWT.DEFAULT) {
      if (columnCount != 0) {
        for (int i = 0; i < columnCount; i++) {
          width += columns[i].getWidth();
        }
      } else {
      }
      if ((style & SWT.CHECK) != 0) {
        width += getCheckColumnWidth();
      }
    } else {
      width = wHint;
    }
    if (hHint == SWT.DEFAULT) {
      height =
          (((int) (((NSTableView) (view)).numberOfRows())) * getItemHeight()) + getHeaderHeight();
    } else {
      height = hHint;
    }
    if (width <= 0) {
      width = DEFAULT_WIDTH;
    }
    if (height <= 0) {
      height = DEFAULT_HEIGHT;
    }
    Rectangle rect = computeTrim(0, 0, width, height);
    return new Point(rect.width, rect.height);
  }
}
