class PlaceHold {
  void onArrowUp(int stateMask) {
    if ((stateMask & (SWT.SHIFT | SWT.CTRL)) == 0) {
      int newFocusIndex = focusItem.availableIndex - 1;
      if (newFocusIndex < 0) {
        return;
      }
      TreeItem item = availableItems[newFocusIndex];
      selectItem(item, false);
      setFocusItem(item, true);
      redrawItem(newFocusIndex, true);
      showItem(item);
      Event newEvent = new Event();
      newEvent.item = item;
      postEvent(Selection, newEvent);
      return;
    }
    if ((style & SWT.SINGLE) != 0) {
      if ((stateMask & SWT.CTRL) != 0) {
        if (topIndex == 0) {
          return;
        }
        update();
        topIndex--;
        getVerticalBar().setSelection(topIndex);
        Rectangle clientArea = getClientArea();
        GC gc = new GC(this);
        gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, itemHeight);
        gc.dispose();
        return;
      }
      int newFocusIndex = focusItem.availableIndex - 1;
      if (newFocusIndex < 0) {
        return;
      }
      TreeItem item = availableItems[newFocusIndex];
      selectItem(item, false);
      setFocusItem(item, true);
      redrawItem(newFocusIndex, true);
      showItem(item);
      Event newEvent = new Event();
      newEvent.item = item;
      postEvent(Selection, newEvent);
      return;
    }
    if ((stateMask & SWT.CTRL) != 0) {
      if ((stateMask & SWT.SHIFT) != 0) {
        if (topIndex == 0) {
          return;
        }
        update();
        topIndex--;
        getVerticalBar().setSelection(topIndex);
        Rectangle clientArea = getClientArea();
        GC gc = new GC(this);
        gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, itemHeight);
        gc.dispose();
        return;
      }
      int focusIndex = focusItem.availableIndex;
      if (focusIndex == 0) {
        return;
      }
      TreeItem newFocusItem = availableItems[focusIndex - 1];
      setFocusItem(newFocusItem, true);
      showItem(newFocusItem);
      redrawItem(newFocusItem.availableIndex, true);
      return;
    }
    int newFocusIndex = focusItem.availableIndex - 1;
    if (newFocusIndex < 0) {
      return;
    }
    if (anchorItem == null) {
      anchorItem = focusItem;
    }
    if (anchorItem.availableIndex < focusItem.availableIndex) {
      deselectItem(focusItem);
      redrawItem(focusItem.availableIndex, true);
    }
    TreeItem item = availableItems[newFocusIndex];
    selectItem(item, true);
    setFocusItem(item, true);
    redrawItem(newFocusIndex, true);
    showItem(item);
    Event newEvent = new Event();
    newEvent.item = item;
    postEvent(Selection, newEvent);
  }
}
