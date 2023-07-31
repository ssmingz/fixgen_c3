class PlaceHold {
  public void remove(int index) {
    checkWidget();
    checkItems(true);
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = items[index];
    if (item != null) {
      item.release(false);
    }
    if (savedAnchor != 0) {
      int savedIndex = getIndex(savedAnchor);
      if (index < savedIndex) {
        savedAnchor = getId(Math.max(0, savedIndex - 1));
      }
    }
    if (index != (itemCount - 1)) {
      fixSelection(index, false);
    }
    int[] id = new int[] {itemCount};
    if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, id.length, id, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
    if (itemCount == 0) {
      setTableEmpty();
    } else {
      fixScrollBar();
    }
  }
}
