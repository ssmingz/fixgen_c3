class PlaceHold {
  void releaseWidget() {
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if ((columnCount == 1) && (columns[0] == null)) {
      columnCount = 0;
    }
    for (int i = 0; i < columnCount; i++) {
      TableColumn column = columns[i];
      if (!column.isDisposed()) {
        column.releaseResources();
      }
    }
    columns = null;
    int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if (OS.IsWin95 && (columnCount > 1)) {
      OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
      for (int i = itemCount - 1; i >= 0; --i) {
        TableItem item = items[i];
        ignoreSelect = ignoreShrink = true;
        OS.SendMessage(handle, LVM_DELETEITEM, i, 0);
        ignoreSelect = ignoreShrink = false;
        if ((item != null) && (!item.isDisposed())) {
          item.releaseResources();
        }
      }
    } else {
      for (int i = 0; i < itemCount; i++) {
        TableItem item = items[i];
        if ((item != null) && (!item.isDisposed())) {
          item.releaseResources();
        }
      }
    }
    customDraw = false;
    items = null;
    if (imageList != null) {
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
      display.releaseImageList(imageList);
    }
    imageList = null;
    int hOldList = OS.SendMessage(handle, LVM_GETIMAGELIST, LVSIL_STATE, 0);
    OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_STATE, 0);
    if (hOldList != 0) {
      OS.ImageList_Destroy(hOldList);
    }
    super.releaseWidget();
  }
}
