class PlaceHold {
  void createItem(TableItem item, int index) {
    int count = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    if (count == items.length) {
      boolean small = (drawCount == 0) && OS.IsWindowVisible(handle);
      int length = (small) ? items.length + 4 : (items.length * 3) / 2;
      TableItem[] newItems = new TableItem[length];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    LVITEM lvItem = new LVITEM();
    lvItem.iItem = index;
    lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
    lvItem.mask |= OS.LVIF_TEXT;
    lvItem.iImage = OS.I_IMAGECALLBACK;
    lvItem.mask |= OS.LVIF_IMAGE;
    ignoreSelect = true;
    int result = OS.SendMessage(handle, LVM_INSERTITEM, 0, lvItem);
    ignoreSelect = false;
    if (result == (-1)) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    System.arraycopy(items, index, items, index + 1, count - index);
    items[index] = item;
  }
}
