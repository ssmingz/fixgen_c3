class PlaceHold {
  public void remove(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    int[] id = new int[] {itemCount};
    if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, id.length, id, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
  }
}
