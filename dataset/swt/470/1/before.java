class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
      int hwndCB = parent.hwndCB;
      TBBUTTONINFO info = new TBBUTTONINFO();
      info.cbSize = TBBUTTONINFO.sizeof;
      info.dwMask = OS.TBIF_STATE;
      OS.SendMessage(hwndCB, TB_GETBUTTONINFO, id, info);
      info.fsState &= ~OS.TBSTATE_ENABLED;
      if (enabled) {
        info.fsState |= OS.TBSTATE_ENABLED;
      }
      OS.SendMessage(hwndCB, TB_SETBUTTONINFO, id, info);
    } else {
      int hMenu = parent.handle;
      if (OS.IsWinCE) {
        int index = parent.indexOf(this);
        if (index == (-1)) {
          return;
        }
        int uEnable = OS.MF_BYPOSITION | (enabled ? OS.MF_ENABLED : OS.MF_GRAYED);
        OS.EnableMenuItem(hMenu, index, uEnable);
      } else {
        MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = OS.MIIM_STATE;
        boolean success = OS.GetMenuItemInfo(hMenu, id, false, info);
        if (!success) {
          error(ERROR_CANNOT_SET_ENABLED);
        }
        int bits = OS.MFS_DISABLED | OS.MFS_GRAYED;
        if (enabled) {
          if ((info.fState & bits) == 0) {
            return;
          }
          info.fState &= ~bits;
        } else {
          if ((info.fState & bits) == bits) {
            return;
          }
          info.fState |= bits;
        }
        success = OS.SetMenuItemInfo(hMenu, id, false, info);
        if (!success) {
          if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
            success = id == OS.GetMenuDefaultItem(hMenu, MF_BYCOMMAND, GMDI_USEDISABLED);
          }
          if (!success) {
            error(ERROR_CANNOT_SET_ENABLED);
          }
        }
      }
    }
    parent.destroyAccelerators();
    parent.redraw();
  }
}
