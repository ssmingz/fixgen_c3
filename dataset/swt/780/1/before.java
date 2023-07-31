class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    if (wHint == SWT.DEFAULT) {
      TreeItem[] items = getItems();
      GC gc = new GC(this);
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        Image image = item.getImage();
        String text = item.getText();
        int itemWidth = 0;
        if (image != null) {
          itemWidth = image.getBounds().width + 2;
        }
        if ((text != null) && (text.length() > 0)) {
          itemWidth += gc.stringExtent(text).x;
        }
        width = Math.max(width, itemWidth);
      }
      gc.dispose();
      width += 20;
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
