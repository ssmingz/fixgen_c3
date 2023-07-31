class PlaceHold {
  void setMenu(Menu menu, boolean dispose) {
    Menu oldMenu = this.menu;
    if (oldMenu == menu) {
      return;
    }
    if (oldMenu != null) {
      oldMenu.cascade = null;
    }
    this.menu = menu;
    if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
      if (OS.IsPPC) {
        int hwndCB = parent.hwndCB;
        int hMenu = (menu == null) ? 0 : menu.handle;
        OS.SendMessage(hwndCB, SHCMBM_SETSUBMENU, id, hMenu);
      }
      if (OS.IsSP) {
        error(ERROR_CANNOT_SET_MENU);
      }
    } else {
      int hMenu = parent.handle;
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      info.fMask = OS.MIIM_DATA;
      int index = 0;
      while (OS.GetMenuItemInfo(hMenu, index, true, info)) {
        if (info.dwItemData == id) {
          break;
        }
        index++;
      }
      if (info.dwItemData != id) {
        return;
      }
      int cch = 128;
      int hHeap = OS.GetProcessHeap();
      int byteCount = cch * TCHAR.sizeof;
      int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
      info.fMask = (OS.MIIM_STATE | OS.MIIM_ID) | OS.MIIM_DATA;
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
        info.fMask |= OS.MIIM_BITMAP | OS.MIIM_STRING;
      } else {
        info.fMask |= OS.MIIM_TYPE;
      }
      info.dwTypeData = pszText;
      info.cch = cch;
      boolean success = OS.GetMenuItemInfo(hMenu, index, true, info);
      if (menu != null) {
        menu.cascade = this;
        info.fMask |= OS.MIIM_SUBMENU;
        info.hSubMenu = menu.handle;
      }
      if (OS.IsWinCE) {
        OS.RemoveMenu(hMenu, index, MF_BYPOSITION);
        int uIDNewItem = id;
        int uFlags = OS.MF_BYPOSITION;
        if (menu != null) {
          uFlags |= OS.MF_POPUP;
          uIDNewItem = menu.handle;
        }
        TCHAR lpNewItem = new TCHAR(0, " ", true);
        success = OS.InsertMenu(hMenu, index, uFlags, uIDNewItem, lpNewItem);
        if (success) {
          info.fMask = OS.MIIM_DATA | OS.MIIM_TYPE;
          success = OS.SetMenuItemInfo(hMenu, index, true, info);
          if ((info.fState & (OS.MFS_DISABLED | OS.MFS_GRAYED)) != 0) {
            OS.EnableMenuItem(hMenu, index, OS.MF_BYPOSITION | OS.MF_GRAYED);
          }
          if ((info.fState & OS.MFS_CHECKED) != 0) {
            OS.CheckMenuItem(hMenu, index, OS.MF_BYPOSITION | OS.MF_CHECKED);
          }
        }
      } else if (dispose || (oldMenu == null)) {
        success = OS.SetMenuItemInfo(hMenu, index, true, info);
      } else {
        OS.RemoveMenu(hMenu, index, MF_BYPOSITION);
        success = OS.InsertMenuItem(hMenu, index, true, info);
      }
      if (pszText != 0) {
        OS.HeapFree(hHeap, 0, pszText);
      }
      if (!success) {
        error(ERROR_CANNOT_SET_MENU);
      }
    }
    parent.destroyAccelerators();
  }
}
