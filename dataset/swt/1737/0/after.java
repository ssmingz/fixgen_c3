class PlaceHold {
  void createItem(TreeItem item, int index) {
    TreeItem[] newItems = new TreeItem[items.length + 1];
    System.arraycopy(items, 0, newItems, 0, index);
    newItems[index] = item;
    System.arraycopy(items, index, newItems, index + 1, items.length - index);
    items = newItems;
    int startIndex;
    if (index == (items.length - 1)) {
      startIndex = availableItemsCount;
    } else {
      startIndex = items[index + 1].availableIndex;
    }
    if (availableItemsCount == availableItems.length) {
      int grow = (drawCount == 0) ? 4 : Math.max(4, (availableItems.length * 3) / 2);
      TreeItem[] newAvailableItems = new TreeItem[availableItems.length + grow];
      System.arraycopy(availableItems, 0, newAvailableItems, 0, availableItems.length);
      availableItems = newAvailableItems;
    }
    if (startIndex != availableItemsCount) {
      System.arraycopy(
          availableItems,
          startIndex,
          availableItems,
          startIndex + 1,
          availableItemsCount - startIndex);
    }
    availableItems[startIndex] = item;
    availableItemsCount++;
    for (int i = startIndex; i < availableItemsCount; i++) {
      availableItems[i].availableIndex = i;
    }
    updateVerticalBar();
    Rectangle bounds = item.getBounds(false);
    int rightX = bounds.x + bounds.width;
    updateHorizontalBar(rightX, rightX);
    if (item.availableIndex < topIndex) {
      topIndex++;
      getVerticalBar().setSelection(topIndex);
      return;
    }
    if ((availableItemsCount == 1) && isFocusControl()) {
      focusItem = item;
      redraw();
      return;
    }
    int redrawIndex = index;
    if ((redrawIndex > 0) && item.isLastChild()) {
      redrawIndex--;
    }
    redrawFromItemDownwards(items[redrawIndex].availableIndex);
  }
}
