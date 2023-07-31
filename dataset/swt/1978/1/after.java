class PlaceHold {
  void doPageUp(int stateMask) {
    if ((stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
      int visibleItemCount = (getClientArea().height - getHeaderHeight()) / itemHeight;
      int newFocusIndex = (focusItem.availableIndex - visibleItemCount) + 1;
      newFocusIndex = Math.max(newFocusIndex, 0);
      TreeItem2 item = availableItems[newFocusIndex];
      selectItem(item, false);
      setFocusItem(item, true);
      showItem(item);
      redrawItem(item.availableIndex);
      return;
    }
  }
}
