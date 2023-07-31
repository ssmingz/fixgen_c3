class PlaceHold {
  void onPageUp(int stateMask) {
    int visibleItemCount = (getClientArea().height - getHeaderHeight()) / itemHeight;
    if ((stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
      int newFocusIndex = Math.max(0, (focusItem.availableIndex - visibleItemCount) + 1);
      if (newFocusIndex == focusItem.availableIndex) {
        return;
      }
      TreeItem item = availableItems[newFocusIndex];
      selectItem(item, false);
      setFocusItem(item, true);
      showItem(item);
      redrawItem(item.availableIndex, true);
      return;
    }
    if ((stateMask & (SWT.CTRL | SWT.SHIFT)) == (SWT.CTRL | SWT.SHIFT)) {
      int newTopIndex = Math.max(0, topIndex - visibleItemCount);
      if (newTopIndex == topIndex) {
        return;
      }
      setTopItem(availableItems[newTopIndex]);
      return;
    }
    if ((style & SWT.SINGLE) != 0) {
      if ((stateMask & SWT.SHIFT) != 0) {
        int newFocusIndex = Math.max(0, (focusItem.availableIndex - visibleItemCount) + 1);
        if (newFocusIndex == focusItem.availableIndex) {
          return;
        }
        TreeItem item = availableItems[newFocusIndex];
        selectItem(item, false);
        setFocusItem(item, true);
        showItem(item);
        redrawItem(item.availableIndex, true);
        return;
      }
      int newTopIndex = Math.max(0, topIndex - visibleItemCount);
      if (newTopIndex == topIndex) {
        return;
      }
      setTopItem(availableItems[newTopIndex]);
      return;
    }
    if ((stateMask & SWT.CTRL) != 0) {
      if (focusItem.availableIndex != topIndex) {
        setFocusItem(availableItems[topIndex], true);
        redrawItem(topIndex, true);
      } else {
        int newFocusIndex = Math.max(0, focusItem.availableIndex - visibleItemCount);
        if (newFocusIndex == focusItem.availableIndex) {
          return;
        }
        setFocusItem(availableItems[newFocusIndex], true);
        showItem(availableItems[newFocusIndex]);
        redrawItem(newFocusIndex, true);
      }
      return;
    }
    if (anchorItem == null) {
      anchorItem = focusItem;
    }
    int anchorIndex = anchorItem.availableIndex;
    int selectIndex;
    if (focusItem.availableIndex != topIndex) {
      selectIndex = topIndex;
    } else {
      selectIndex = Math.max(0, topIndex - visibleItemCount);
      if ((selectIndex == focusItem.availableIndex) && focusItem.isSelected()) {
        return;
      }
    }
    TreeItem selectedItem = availableItems[selectIndex];
    TreeItem[] newSelection = new TreeItem[Math.abs(anchorIndex - selectIndex) + 1];
    int step = (anchorIndex < selectIndex) ? 1 : -1;
    int writeIndex = 0;
    for (int i = anchorIndex; i != selectIndex; i += step) {
      newSelection[writeIndex++] = availableItems[i];
    }
    newSelection[writeIndex] = availableItems[selectIndex];
    setSelection(newSelection);
    setFocusItem(selectedItem, true);
    showItem(selectedItem);
    Event newEvent = new Event();
    newEvent.item = selectedItem;
    sendEvent(Selection, newEvent);
  }
}
