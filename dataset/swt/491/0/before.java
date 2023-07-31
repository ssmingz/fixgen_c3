class PlaceHold {
  void onArrowDown(int stateMask) {
    if ((stateMask & (SWT.SHIFT | SWT.CTRL)) == 0) {
      int newFocusIndex = focusItem.availableIndex + 1;
      if (newFocusIndex == availableItemsCount) {
        return;
      }
      selectItem(availableItems[newFocusIndex], false);
      setFocusItem(availableItems[newFocusIndex], true);
      redrawItem(newFocusIndex, true);
      showItem(availableItems[newFocusIndex]);
      Event newEvent = new Event();
      newEvent.item = availableItems[newFocusIndex];
      postEvent(Selection, newEvent);
      return;
    }
    if ((style & SWT.SINGLE) != 0) {
      if ((stateMask & SWT.CTRL) != 0) {
        Rectangle clientArea = getClientArea();
        int visibleItemCount = (clientArea.height - getHeaderHeight()) / itemHeight;
        if (availableItemsCount <= (topIndex + visibleItemCount)) {
          return;
        }
        update();
        topIndex++;
        getVerticalBar().setSelection(topIndex);
        GC gc = new GC(this);
        gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, -itemHeight);
        gc.dispose();
        return;
      }
      int newFocusIndex = focusItem.availableIndex + 1;
      if (newFocusIndex == availableItemsCount) {
        return;
      }
      selectItem(availableItems[newFocusIndex], false);
      setFocusItem(availableItems[newFocusIndex], true);
      redrawItem(newFocusIndex, true);
      showItem(availableItems[newFocusIndex]);
      Event newEvent = new Event();
      newEvent.item = availableItems[newFocusIndex];
      postEvent(Selection, newEvent);
      return;
    }
    if ((stateMask & SWT.CTRL) != 0) {
      if ((stateMask & SWT.SHIFT) != 0) {
        Rectangle clientArea = getClientArea();
        int visibleItemCount = (clientArea.height - getHeaderHeight()) / itemHeight;
        if (availableItemsCount <= (topIndex + visibleItemCount)) {
          return;
        }
        update();
        topIndex++;
        getVerticalBar().setSelection(topIndex);
        GC gc = new GC(this);
        gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, -itemHeight);
        gc.dispose();
        return;
      }
      int focusIndex = focusItem.availableIndex;
      if (focusIndex == (availableItemsCount - 1)) {
        return;
      }
      TreeItem newFocusItem = availableItems[focusIndex + 1];
      setFocusItem(newFocusItem, true);
      redrawItem(newFocusItem.availableIndex, true);
      showItem(newFocusItem);
      return;
    }
    int newFocusIndex = focusItem.availableIndex + 1;
    if (newFocusIndex == availableItemsCount) {
      return;
    }
    if (anchorItem == null) {
      anchorItem = focusItem;
    }
    selectItem(availableItems[newFocusIndex], true);
    setFocusItem(availableItems[newFocusIndex], true);
    redrawItem(newFocusIndex, true);
    showItem(availableItems[newFocusIndex]);
    Event newEvent = new Event();
    newEvent.item = availableItems[newFocusIndex];
    postEvent(Selection, newEvent);
  }
}
