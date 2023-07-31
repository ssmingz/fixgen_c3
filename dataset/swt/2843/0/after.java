class PlaceHold {
  void destroyItem(TableItem item) {
    checkItems(true);
    int index = 0;
    while (index < itemCount) {
      if (items[index] == item) {
        break;
      }
      index++;
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
    }
  }
}
