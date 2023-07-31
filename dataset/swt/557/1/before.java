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
      redrawStart = (count > 0) ? items[count - 1].availableIndex : availableIndex;
      redrawEnd = parent.availableItemsCount - 1;
      for (int i = count; i < items.length; i++) {
        items[i].dispose(true);
      }
      if (count == 0) {
        items = NO_ITEMS;
      } else {
        TreeItem[] newItems = new TreeItem[count];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
      }
      if (count == 0) {
        expanded = false;
      }
    } else {
      int oldAvailableDescendentCount = computeAvailableDescendentCount();
      int grow = count - items.length;
      redrawStart = (items.length == 0) ? availableIndex : items[items.length - 1].availableIndex;
      redrawEnd =
          (expanded && isAvailable()) ? (parent.availableItemsCount + grow) - 1 : redrawStart;
      TreeItem[] newItems = new TreeItem[count];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
      for (int i = items.length - grow; i < count; i++) {
        items[i] = new TreeItem(this, SWT.NONE, i, false);
      }
      if (expanded && (availableIndex != (-1))) {
        if (parent.availableItems.length < (parent.availableItemsCount + grow)) {
          TreeItem[] newAvailableItems = new TreeItem[parent.availableItemsCount + grow];
          System.arraycopy(
              parent.availableItems, 0, newAvailableItems, 0, parent.availableItemsCount);
          parent.availableItems = newAvailableItems;
        }
        TreeItem[] availableItems = parent.availableItems;
        int dest = (availableIndex + oldAvailableDescendentCount) + grow;
        System.arraycopy(
            availableItems,
            availableIndex + oldAvailableDescendentCount,
            availableItems,
            dest,
            availableItems.length - dest);
        parent.availableItemsCount += grow;
        System.arraycopy(
            items,
            items.length - grow,
            availableItems,
            availableIndex + oldAvailableDescendentCount,
            grow);
        for (int i = availableIndex + oldAvailableDescendentCount;
            i < parent.availableItemsCount;
            i++) {
          availableItems[i].availableIndex = i;
        }
      }
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (availableIndex != (-1)) {
      if (expanded) {
        parent.updateVerticalBar();
      }
      parent.redrawItems(redrawStart, redrawEnd, false);
    }
  }
}
