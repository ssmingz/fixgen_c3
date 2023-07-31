class PlaceHold {
  void destroyItem(TreeItem item) {
    int availableIndex = item.availableIndex;
    if (availableIndex != (-1)) {
      Rectangle bounds = item.getBounds();
      int rightX = bounds.x + bounds.width;
      TreeItem[] newAvailableItems = new TreeItem[availableItems.length - 1];
      System.arraycopy(availableItems, 0, newAvailableItems, 0, availableIndex);
      System.arraycopy(
          availableItems,
          availableIndex + 1,
          newAvailableItems,
          availableIndex,
          newAvailableItems.length - availableIndex);
      availableItems = newAvailableItems;
      for (int i = availableIndex; i < availableItems.length; i++) {
        availableItems[i].availableIndex = i;
      }
      item.availableIndex = -1;
      updateVerticalBar();
      updateHorizontalBar(0, -rightX);
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
    if (item == focusItem) {
      reassignFocus();
    }
    if (item == anchorItem) {
      anchorItem = null;
    }
    if (item == insertMarkItem) {
      insertMarkItem = null;
    }
  }
}
