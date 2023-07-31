class PlaceHold {
  void destroyItem(TableItem item) {
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    int index = 0;
    while (index < count) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index == count) {
      return;
    }
    setDeferResize(true);
    ignoreSelect = ignoreShrink = true;
    int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
    ignoreSelect = ignoreShrink = false;
    if (code == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--count) - index);
    items[count] = null;
    if (count == 0) {
      setTableEmpty();
    }
    setDeferResize(false);
  }
}
