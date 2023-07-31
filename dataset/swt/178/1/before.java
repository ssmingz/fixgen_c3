class PlaceHold {
  public void remove(int index) {
    checkWidget();
    checkItems(true);
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (index != (itemCount - 1)) {
      fixSelection(index, false);
    }
    int[] id = new int[] {itemCount};
    if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, id.length, id, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    TableItem item = items[index];
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    if (item != null) {
      item.releaseResources();
    }
    OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
    if (itemCount == 0) {
      setTableEmpty();
    }
  }
}
