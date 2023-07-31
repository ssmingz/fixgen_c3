class PlaceHold {
  public void setSelection(boolean selected) {
    checkWidget();
    if ((style & (SWT.CHECK | SWT.RADIO)) == 0) {
      return;
    }
    if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
      return;
    }
    int hMenu = parent.handle;
    if (OS.IsWinCE) {
      int index = parent.indexOf(this);
      if (index == (-1)) {
        return;
      }
      int uCheck = OS.MF_BYPOSITION | (selected ? OS.MF_CHECKED : OS.MF_UNCHECKED);
      OS.CheckMenuItem(hMenu, index, uCheck);
    } else {
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      info.fMask = OS.MIIM_STATE;
      boolean success = OS.GetMenuItemInfo(hMenu, id, false, info);
      if (!success) {
        error(ERROR_CANNOT_SET_SELECTION);
      }
      info.fState &= ~OS.MFS_CHECKED;
      if (selected) {
        info.fState |= OS.MFS_CHECKED;
      }
      success = OS.SetMenuItemInfo(hMenu, id, false, info);
      if (!success) {
        if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          success = id == OS.GetMenuDefaultItem(hMenu, MF_BYCOMMAND, GMDI_USEDISABLED);
        }
        if (!success) {
          error(ERROR_CANNOT_SET_SELECTION);
        }
      }
    }
    parent.redraw();
  }
}
