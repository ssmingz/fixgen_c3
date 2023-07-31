class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    Point size = super.computeSize(wHint, hHint, changed);
    Point headerSize;
    GC gc;
    final int WidthCalculationCount = Math.min(getItemCount(), 50);
    TableItem item;
    Image itemImage;
    String itemText;
    int width;
    int newItemWidth = 0;
    if ((getHeaderVisible() == true) && (hHint == SWT.DEFAULT)) {
      headerSize = getHeader().computeSize(DEFAULT, DEFAULT, false);
      size.y += headerSize.y;
    }
    if ((getContentWidth() == 0) && (WidthCalculationCount > 0)) {
      gc = new GC(this);
      for (int i = 0; i < WidthCalculationCount; i++) {
        item = getItem(i);
        if (item == null) {
          break;
        }
        itemImage = item.getImage();
        itemText = item.getText();
        width = 0;
        if (itemImage != null) {
          width += itemImage.getBounds().width;
        }
        if (itemText != null) {
          width += gc.stringExtent(itemText).x;
        }
        newItemWidth = Math.max(newItemWidth, width);
      }
      if (newItemWidth > 0) {
        size.x = newItemWidth;
      }
      gc.dispose();
    }
    return size;
  }
}
