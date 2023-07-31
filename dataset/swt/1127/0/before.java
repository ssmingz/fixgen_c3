class PlaceHold {
  void destroyItem(TableItem item) {
    int count = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
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
    ignoreSelect = true;
    int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
    ignoreSelect = false;
    if (code == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--count) - index);
    items[count] = null;
    if (count == 0) {
      if (imageList != null) {
        OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
        Display display = getDisplay();
        display.releaseImageList(imageList);
      }
      imageList = null;
      items = new TableItem[4];
    }
  }
}
