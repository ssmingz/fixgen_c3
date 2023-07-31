class PlaceHold {
  public void showColumn(TableColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column.parent != this) {
      return;
    }
    int index = indexOf(column);
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if ((count <= 1) || (!((0 <= index) && (index < count)))) {
      return;
    }
    RECT rect = new RECT();
    rect.left = OS.LVIR_BOUNDS;
    if (index == 0) {
      rect.top = 1;
      OS.SendMessage(handle, LVM_GETSUBITEMRECT, -1, rect);
      rect.right = rect.left;
      int width = OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0);
      rect.left = rect.right - width;
    } else {
      rect.top = index;
      OS.SendMessage(handle, LVM_GETSUBITEMRECT, -1, rect);
    }
    RECT area = new RECT();
    OS.GetClientRect(handle, area);
    if (rect.left < area.left) {
      int dx = rect.left - area.left;
      OS.SendMessage(handle, LVM_SCROLL, dx, 0);
    } else {
      int width = Math.min(area.right - area.left, rect.right - rect.left);
      if ((rect.left + width) > area.right) {
        int dx = (rect.left + width) - area.right;
        OS.SendMessage(handle, LVM_SCROLL, dx, 0);
      }
    }
  }
}
