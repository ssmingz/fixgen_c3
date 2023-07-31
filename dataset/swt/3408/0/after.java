class PlaceHold {
  public MenuItem getItem(int index) {
    checkWidget();
    int id = 0;
    if ((OS.IsPPC || OS.IsSP) && (hwndCB != 0)) {
      if (OS.IsPPC) {
        TBBUTTON lpButton = new TBBUTTON();
        long result = OS.SendMessage(hwndCB, TB_GETBUTTON, index, lpButton);
        if (result == 0) {
          error(ERROR_CANNOT_GET_ITEM);
        }
        id = lpButton.idCommand;
      }
      if (OS.IsSP) {
        if (!((0 <= index) && (index <= 1))) {
          error(ERROR_CANNOT_GET_ITEM);
        }
        id = (index == 0) ? id0 : id1;
      }
    } else {
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      info.fMask = OS.MIIM_DATA;
      if (!OS.GetMenuItemInfo(handle, index, true, info)) {
        error(ERROR_INVALID_RANGE);
      }
      id = ((int) (info.dwItemData));
    }
    return display.getMenuItem(id);
  }
}
