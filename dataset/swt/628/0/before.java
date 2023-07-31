class PlaceHold {
  public void add(String string, int index) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    int[] id = new int[] {itemCount + 1};
    if (OS.AddDataBrowserItems(handle, kDataBrowserNoItem, 1, id, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    if (itemCount == items.length) {
      String[] newItems = new String[itemCount + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = string;
    OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
  }
}
