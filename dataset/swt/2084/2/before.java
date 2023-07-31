class PlaceHold {
  public void removeAll() {
    checkWidget();
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if ((columnCount == 1) && (columns[0] == null)) {
      columnCount = 0;
    }
    int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if (columnCount > 1) {
      boolean redraw = (drawCount == 0) && OS.IsWindowVisible(handle);
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
      }
      int index = itemCount - 1;
      while (index >= 0) {
        ignoreSelect = true;
        int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
        ignoreSelect = false;
        if (code == 0) {
          break;
        }
        items[index].releaseResources();
        --index;
      }
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
      }
      if (index != (-1)) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    } else {
      ignoreSelect = true;
      int code = OS.SendMessage(handle, LVM_DELETEALLITEMS, 0, 0);
      ignoreSelect = false;
      if (code == 0) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
      for (int i = 0; i < itemCount; i++) {
        TableItem item = items[i];
        if (!item.isDisposed()) {
          item.releaseResources();
        }
      }
    }
    if (imageList != null) {
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
        Display display = getDisplay();
        display.releaseImageList(imageList);
        imageList = null;
      }
    }
    customDraw = false;
    items = new TableItem[4];
  }
}
