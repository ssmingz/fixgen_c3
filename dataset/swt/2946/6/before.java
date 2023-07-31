class PlaceHold {
  void destroyItem(TreeItem item) {
    if (item == focusItem) {
      reassignFocus();
    }
    int availableIndex = item.availableIndex;
    if (availableIndex != (-1)) {
      Rectangle bounds = item.getBounds(false);
      int rightX = bounds.x + bounds.width;
      if (availableIndex != (availableItemsCount - 1)) {
        System.arraycopy(
            availableItems,
            availableIndex + 1,
            availableItems,
            availableIndex,
            (availableItemsCount - availableIndex) - 1);
        availableItems[availableItemsCount - 1] = null;
      } else {
        availableItems[availableIndex] = null;
      }
      availableItemsCount--;
      if ((drawCount <= 0) && ((availableItems.length - availableItemsCount) == 4)) {
        TreeItem[] newAvailableItems = new TreeItem[availableItemsCount];
        System.arraycopy(availableItems, 0, newAvailableItems, 0, newAvailableItems.length);
        availableItems = newAvailableItems;
      }
      for (int i = availableIndex; i < availableItemsCount; i++) {
        availableItems[i].availableIndex = i;
      }
      item.availableIndex = -1;
      int oldTopIndex = topIndex;
      updateVerticalBar();
      updateHorizontalBar(0, -rightX);
      if (availableIndex < topIndex) {
        topIndex = oldTopIndex - 1;
        getVerticalBar().setSelection(topIndex);
      }
    }
    if (item.isSelected()) {
      int selectionIndex = getSelectionIndex(item);
      TreeItem[] newSelectedItems = new TreeItem[selectedItems.length - 1];
      System.arraycopy(selectedItems, 0, newSelectedItems, 0, selectionIndex);
      System.arraycopy(
          selectedItems,
          selectionIndex + 1,
          newSelectedItems,
          selectionIndex,
          newSelectedItems.length - selectionIndex);
      selectedItems = newSelectedItems;
    }
    if (item.depth == 0) {
      int index = item.getIndex();
      TreeItem[] newItems = new TreeItem[items.length - 1];
      System.arraycopy(items, 0, newItems, 0, index);
      System.arraycopy(items, index + 1, newItems, index, newItems.length - index);
      items = newItems;
    }
    if (item == anchorItem) {
      anchorItem = null;
    }
    if (item == insertMarkItem) {
      insertMarkItem = null;
    }
    if (item == lastClickedItem) {
      lastClickedItem = null;
    }
    if ((availableItemsCount == 0) && isFocusControl()) {
      redraw();
      return;
    }
  }
}
