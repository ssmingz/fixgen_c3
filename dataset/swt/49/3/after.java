class PlaceHold {
  void destroyItem(TableItem item) {
    if (item == focusItem) {
      reassignFocus();
    }
    int index = item.index;
    Rectangle bounds = item.getBounds(false);
    int rightX = bounds.x + bounds.width;
    if (index != (itemsCount - 1)) {
      System.arraycopy(items, index + 1, items, index, (itemsCount - index) - 1);
      items[itemsCount - 1] = null;
    } else {
      items[index] = null;
    }
    itemsCount--;
    if ((drawCount <= 0) && ((items.length - itemsCount) == 4)) {
      TableItem[] newItems = new TableItem[itemsCount];
      System.arraycopy(items, 0, newItems, 0, newItems.length);
      items = newItems;
    }
    for (int i = index; i < itemsCount; i++) {
      items[i].index = i;
    }
    item.index = -1;
    int oldTopIndex = topIndex;
    updateVerticalBar();
    updateHorizontalBar(0, -rightX);
    if (index < topIndex) {
      topIndex = oldTopIndex - 1;
      ScrollBar vBar = getVerticalBar();
      if (vBar != null) {
        vBar.setSelection(topIndex);
      }
    }
    if (item.isSelected()) {
      int selectionIndex = getSelectionIndex(item);
      TableItem[] newSelectedItems = new TableItem[selectedItems.length - 1];
      System.arraycopy(selectedItems, 0, newSelectedItems, 0, selectionIndex);
      System.arraycopy(
          selectedItems,
          selectionIndex + 1,
          newSelectedItems,
          selectionIndex,
          newSelectedItems.length - selectionIndex);
      selectedItems = newSelectedItems;
    }
    if (item == anchorItem) {
      anchorItem = null;
    }
    if (item == lastClickedItem) {
      lastClickedItem = null;
    }
    if ((itemsCount == 0) && isFocusControl()) {
      redraw();
      return;
    }
  }
}
