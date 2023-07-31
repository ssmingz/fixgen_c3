class PlaceHold {
  public void setMenu(Menu menu) {
    checkWidget();
    if ((style & SWT.CASCADE) == 0) {
      error(ERROR_MENUITEM_NOT_CASCADE);
    }
    if (menu != null) {
      if (menu.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if ((menu.style & SWT.DROP_DOWN) == 0) {
        error(ERROR_MENU_NOT_DROP_DOWN);
      }
      if (menu.parent != parent.parent) {
        error(ERROR_INVALID_PARENT);
      }
    }
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
      boolean success = false;
      if (!OS.IsWinCE) {
        if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10)) {
          if (image != null) {
            info.fMask = OS.MIIM_BITMAP;
            info.hbmpItem = 0;
            success = OS.SetMenuItemInfo(hMenu, id, false, info);
          }
        }
      }
      int cch = 128;
      int hHeap = OS.GetProcessHeap();
      int byteCount = cch * TCHAR.sizeof;
      int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
      info.fMask = ((OS.MIIM_STATE | OS.MIIM_ID) | OS.MIIM_TYPE) | OS.MIIM_DATA;
      info.dwTypeData = pszText;
      info.cch = cch;
      success = OS.GetMenuItemInfo(hMenu, index, true, info);
      if (menu != null) {
        menu.cascade = this;
        info.fMask |= OS.MIIM_SUBMENU;
        info.hSubMenu = menu.handle;
      }
      OS.RemoveMenu(hMenu, index, MF_BYPOSITION);
      if (OS.IsWinCE) {
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
      } else {
        success = OS.InsertMenuItem(hMenu, index, true, info);
        if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10)) {
          if (image != null) {
            info.fMask = OS.MIIM_BITMAP;
            info.hbmpItem = OS.HBMMENU_CALLBACK;
            success = OS.SetMenuItemInfo(hMenu, id, false, info);
          }
        }
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
