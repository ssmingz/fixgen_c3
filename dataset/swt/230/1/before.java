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
          Image image = item.getImage(j);
          String text = item.getText(j);
          int itemWidth = 0;
          if (image != null) {
            itemWidth = image.getBounds().width + 2;
          }
          if ((text != null) && (text.length() > 0)) {
            itemWidth += gc.stringExtent(text).x;
          }
          columnWidth = Math.max(columnWidth, itemWidth);
        }
        width += columnWidth + 20;
      }
      gc.dispose();
      if ((style & SWT.CHECK) != 0) {
        width += 35 + 20;
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
