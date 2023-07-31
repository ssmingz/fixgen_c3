class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    Point size = super.computeSize(wHint, hHint, changed);
    GC gc;
    final int WidthCalculationCount = 50;
    TreeRoots root = getRoot();
    TreeItem item;
    Image itemImage;
    String itemText;
    int width;
    int newItemWidth = 0;
    if (((wHint == SWT.DEFAULT) && (getContentWidth() == 0)) && (getItemCount() > 0)) {
      gc = new GC(this);
      for (int i = 0; i < WidthCalculationCount; i++) {
        item = root.getVisibleItem(i);
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
          gc.setFont(item.getFont());
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
