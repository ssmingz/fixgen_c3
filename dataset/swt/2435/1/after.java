class PlaceHold {
  public void removeAll() {
    checkWidget();
    int itemCount = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    setDeferResize(true);
    if (OS.IsWin95 && (columnCount > 1)) {
      boolean redraw = getDrawing() && OS.IsWindowVisible(handle);
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
      }
      int index = itemCount - 1;
      while (index >= 0) {
        ignoreSelect = ignoreShrink = true;
        int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
        ignoreSelect = ignoreShrink = false;
        if (code == 0) {
          break;
        }
        --index;
      }
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
      }
      if (index != (-1)) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    } else {
      ignoreSelect = ignoreShrink = true;
      int code = OS.SendMessage(handle, LVM_DELETEALLITEMS, 0, 0);
      ignoreSelect = ignoreShrink = false;
      if (code == 0) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    }
    setTableEmpty();
    setDeferResize(false);
  }
}
