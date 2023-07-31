class PlaceHold {
  int GetMenuItemCount(int handle) {
    if (OS.IsWinCE) {
      if ((OS.IsPPC || OS.IsSP) && (hwndCB != 0)) {
        return OS.IsSP ? 2 : ((int) (OS.SendMessage(hwndCB, TB_BUTTONCOUNT, 0, 0)));
      }
      int count = 0;
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      while (OS.GetMenuItemInfo(handle, count, true, info)) {
        count++;
      }
      return count;
    }
    return OS.GetMenuItemCount(handle);
  }
}
