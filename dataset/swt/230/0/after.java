class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    if (wHint == SWT.DEFAULT) {
      TreeItem[] items = getItems();
      GC gc = new GC(this);
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        width = Math.max(width, item.calculateWidth(gc));
      }
      gc.dispose();
      width += EXTRA_WIDTH;
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
      height = getItemCount() * getItemHeight();
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
