class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
      int hwndCB = parent.hwndCB;
      TBBUTTONINFO info = new TBBUTTONINFO();
      info.cbSize = TBBUTTONINFO.sizeof;
      info.dwMask = OS.TBIF_STATE;
      OS.SendMessage(hwndCB, TB_GETBUTTONINFO, id, info);
      return (info.fsState & OS.TBSTATE_ENABLED) != 0;
    }
    int hMenu = parent.handle;
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_STATE;
    boolean success;
    if (OS.IsWinCE) {
      int index = parent.indexOf(this);
      if (index == (-1)) {
        error(ERROR_CANNOT_GET_ENABLED);
      }
      success = OS.GetMenuItemInfo(hMenu, index, true, info);
    } else {
      success = OS.GetMenuItemInfo(hMenu, id, false, info);
    }
    if (!success) {
      error(ERROR_CANNOT_GET_ENABLED);
    }
    return (info.fState & (OS.MFS_DISABLED | OS.MFS_GRAYED)) == 0;
  }
}
