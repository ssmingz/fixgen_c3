class PlaceHold {
  void createItem(TableItem item, int index) {
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    boolean add = (drawCount == 0) || (index != itemCount);
    if (add) {
      int[] id = new int[] {itemCount + 1};
      if (OS.AddDataBrowserItems(handle, kDataBrowserNoItem, 1, id, 0) != OS.noErr) {
        error(ERROR_ITEM_NOT_ADDED);
      }
    }
    if (itemCount == items.length) {
      int newLength = (drawCount == 0) ? items.length + 4 : (items.length * 3) / 2;
      TableItem[] newItems = new TableItem[newLength];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
    if (add) {
      OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
    }
  }
}
