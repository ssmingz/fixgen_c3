class PlaceHold {
  void setItemCount(int count) {
    checkWidget();
    count = Math.max(0, count);
    if (count == items.length) {
      return;
    }
    int redrawStart;
    int redrawEnd;
    if (count < items.length) {
      redrawStart = (count > 0) ? items[count - 1].availableIndex : 0;
      redrawEnd = availableItemsCount - 1;
      availableItemsCount = items[count].availableIndex;
      for (int i = count; i < items.length; i++) {
        items[i].dispose(false);
      }
      if (count == 0) {
        items = TreeItem.NO_ITEMS;
      } else {
        TreeItem[] newItems = new TreeItem[count];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
      }
      int newSelectedCount = 0;
      for (int i = 0; i < selectedItems.length; i++) {
        if (!selectedItems[i].isDisposed()) {
          newSelectedCount++;
        }
      }
      if (newSelectedCount != selectedItems.length) {
        TreeItem[] newSelectedItems = new TreeItem[newSelectedCount];
        int pos = 0;
        for (int i = 0; i < selectedItems.length; i++) {
          TreeItem item = selectedItems[i];
          if (!item.isDisposed()) {
            newSelectedItems[pos++] = item;
          }
        }
        selectedItems = newSelectedItems;
      }
      if ((insertMarkItem != null) && insertMarkItem.isDisposed()) {
        insertMarkItem = null;
      }
      if ((lastClickedItem != null) && lastClickedItem.isDisposed()) {
        lastClickedItem = null;
      }
      if ((anchorItem != null) && anchorItem.isDisposed()) {
        anchorItem = null;
      }
      if ((focusItem != null) && focusItem.isDisposed()) {
        TreeItem newFocusItem = (count > 0) ? items[count - 1] : null;
        setFocusItem(newFocusItem, false);
      }
      if (columns.length == 0) {
        updateHorizontalBar();
      }
    } else {
      int grow = count - items.length;
      redrawStart = (items.length == 0) ? 0 : items[items.length - 1].availableIndex;
      redrawEnd = (availableItemsCount + grow) - 1;
      TreeItem[] newItems = new TreeItem[count];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
      if (availableItems.length < (availableItemsCount + grow)) {
        TreeItem[] newAvailableItems = new TreeItem[availableItemsCount + grow];
        System.arraycopy(availableItems, 0, newAvailableItems, 0, availableItemsCount);
        availableItems = newAvailableItems;
      }
      for (int i = items.length - grow; i < count; i++) {
        TreeItem newItem = new TreeItem(this, SWT.NONE, i, false);
        items[i] = newItem;
        items[i].availableIndex = availableItemsCount;
        availableItems[availableItemsCount++] = newItem;
      }
    }
    updateVerticalBar();
    redrawItems(redrawStart, redrawEnd, false);
  }
}
