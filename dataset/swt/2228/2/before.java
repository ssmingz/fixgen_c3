class PlaceHold {
  void createItem(TableItem item) {
    int index = item.index;
    if (itemsCount == items.length) {
      int grow = (drawCount == 0) ? 4 : Math.max(4, (items.length * 3) / 2);
      TableItem[] newItems = new TableItem[items.length + grow];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    if (index != itemsCount) {
      System.arraycopy(items, index, items, index + 1, itemsCount - index);
    }
    items[index] = item;
    itemsCount++;
    for (int i = index + 1; i < itemsCount; i++) {
      items[i].index = i;
    }
    updateVerticalBar();
    Rectangle bounds = item.getBounds(false);
    int rightX = bounds.x + bounds.width;
    updateHorizontalBar(rightX, rightX);
    if (item.index < topIndex) {
      topIndex++;
      getVerticalBar().setSelection(topIndex);
      return;
    }
    if ((itemsCount == 1) && isFocusControl()) {
      focusItem = item;
      redraw();
      return;
    }
    if (item.isInViewport()) {
      redrawFromItemDownwards(index);
    }
  }
}
