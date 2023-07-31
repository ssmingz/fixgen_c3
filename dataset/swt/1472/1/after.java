class PlaceHold {
  public int indexOf(MenuItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (item.parent != this) {
      return -1;
    }
    if ((OS.IsPPC || OS.IsSP) && (hwndCB != 0)) {
      if (OS.IsPPC) {
        return ((int) (OS.SendMessage(hwndCB, TB_COMMANDTOINDEX, item.id, 0)));
      }
      if (OS.IsSP) {
        if (item.id == id0) {
          return 0;
        }
        if (item.id == id1) {
          return 1;
        }
        return -1;
      }
    }
    int index = 0;
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_DATA;
    while (OS.GetMenuItemInfo(handle, index, true, info)) {
      if (info.dwItemData == item.id) {
        return index;
      }
      index++;
    }
    return -1;
  }
}
