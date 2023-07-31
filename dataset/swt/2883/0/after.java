class PlaceHold {
  public void add(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int[] id = new int[] {itemCount + 1};
    if (OS.AddDataBrowserItems(handle, kDataBrowserNoItem, 1, id, kDataBrowserItemNoProperty)
        != OS.noErr) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    if (itemCount == items.length) {
      String[] newItems = new String[itemCount + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    items[itemCount++] = string;
  }
}
