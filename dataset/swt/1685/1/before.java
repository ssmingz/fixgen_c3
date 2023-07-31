class PlaceHold {
  void createItem(TableItem item, int index) {
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    if (count == items.length) {
      boolean small = (drawCount == 0) && OS.IsWindowVisible(handle);
      int length = (small) ? items.length + 4 : Math.max(4, (items.length * 3) / 2);
      TableItem[] newItems = new TableItem[length];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_TEXT | OS.LVIF_IMAGE;
    lvItem.iItem = index;
    lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
    lvItem.iImage = OS.I_IMAGECALLBACK;
    setDeferResize(true);
    ignoreSelect = ignoreShrink = true;
    int result = ((int) (OS.SendMessage(handle, LVM_INSERTITEM, 0, lvItem)));
    ignoreSelect = ignoreShrink = false;
    if (result == (-1)) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    System.arraycopy(items, index, items, index + 1, count - index);
    items[index] = item;
    setDeferResize(false);
    if (count == 0) {
      setScrollWidth(item, false);
    }
  }
}
