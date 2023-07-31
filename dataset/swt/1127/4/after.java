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
        column.releaseWidget();
      }
    }
    columns = null;
    int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
    for (int i = itemCount - 1; i >= 0; --i) {
      ignoreSelect = true;
      OS.SendMessage(handle, LVM_DELETEITEM, i, 0);
      ignoreSelect = false;
      TableItem item = items[i];
      if (!item.isDisposed()) {
        item.releaseWidget();
      }
    }
    customDraw = false;
    items = null;
    if (imageList != null) {
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, 0);
      Display display = getDisplay();
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
