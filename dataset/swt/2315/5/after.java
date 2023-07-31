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
    ignoreSelect = ignoreShrink = true;
    int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
    ignoreSelect = ignoreShrink = false;
    if (code == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(items, index + 1, items, index, (--count) - index);
    items[count] = null;
    if (count == 0) {
      if (imageList != null) {
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
        if ((columnCount == 1) && (columns[0] == null)) {
          columnCount = 0;
        }
        int i = 0;
        while (i < columnCount) {
          TableColumn column = columns[i];
          if (column.getImage() != null) {
            break;
          }
          i++;
        }
        if (i == columnCount) {
          OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
          display.releaseImageList(imageList);
          imageList = null;
        }
      }
      customDraw = false;
      items = new TableItem[4];
    }
  }
}
