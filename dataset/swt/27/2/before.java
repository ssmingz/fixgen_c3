class PlaceHold {
  void createItem(TreeItem item, int index) {
    TreeItem[] newItems = new TreeItem[items.length + 1];
    System.arraycopy(items, 0, newItems, 0, index);
    newItems[index] = item;
    System.arraycopy(items, index, newItems, index + 1, items.length - index);
    items = newItems;
    int startIndex;
    if (index == (items.length - 1)) {
      startIndex = availableItems.length;
    } else {
      startIndex = items[index + 1].availableIndex;
    }
    TreeItem[] newAvailableItems = new TreeItem[availableItems.length + 1];
    System.arraycopy(availableItems, 0, newAvailableItems, 0, startIndex);
    newAvailableItems[startIndex] = item;
    System.arraycopy(
        availableItems,
        startIndex,
        newAvailableItems,
        startIndex + 1,
        (newAvailableItems.length - startIndex) - 1);
    availableItems = newAvailableItems;
    for (int i = startIndex; i < availableItems.length; i++) {
      availableItems[i].availableIndex = i;
    }
    updateVerticalBar();
    updateHorizontalBar();
    int redrawIndex = index;
    if (redrawIndex > 0) {
      redrawIndex--;
    }
    redrawFromItemDownwards(items[redrawIndex].availableIndex);
  }
}
