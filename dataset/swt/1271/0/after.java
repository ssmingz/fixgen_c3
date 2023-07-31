class PlaceHold {
  LRESULT WM_MENUSELECT(int wParam, int lParam) {
    int code = wParam >> 16;
    Shell shell = getShell();
    if ((code == (-1)) && (lParam == 0)) {
      Menu menu = shell.activeMenu;
      while (menu != null) {
        display.mnemonicKeyHit = true;
        menu.sendEvent(Hide);
        if (menu.isDisposed()) {
          break;
        }
        menu = menu.getParentMenu();
      }
      shell.activeMenu = null;
      return null;
    }
    if ((code & OS.MF_SYSMENU) != 0) {
      return null;
    }
    if ((code & OS.MF_HILITE) != 0) {
      MenuItem item = null;
      Decorations menuShell = menuShell();
      if ((code & OS.MF_POPUP) != 0) {
        int index = wParam & 0xffff;
        MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = OS.MIIM_SUBMENU;
        if (OS.GetMenuItemInfo(lParam, index, true, info)) {
          Menu newMenu = menuShell.findMenu(info.hSubMenu);
          if (newMenu != null) {
            item = newMenu.cascade;
          }
        }
      } else {
        Menu newMenu = menuShell.findMenu(lParam);
        if (newMenu != null) {
          int id = wParam & 0xffff;
          item = display.getMenuItem(id);
        }
        Menu oldMenu = shell.activeMenu;
        if (oldMenu != null) {
          Menu ancestor = oldMenu;
          while ((ancestor != null) && (ancestor != newMenu)) {
            ancestor = ancestor.getParentMenu();
          }
          if (ancestor == newMenu) {
            ancestor = oldMenu;
            while (ancestor != newMenu) {
              ancestor.sendEvent(Hide);
              if (ancestor.isDisposed()) {
                break;
              }
              ancestor = ancestor.getParentMenu();
            }
            if (!shell.isDisposed()) {
              if ((newMenu != null) && newMenu.isDisposed()) {
                newMenu = null;
              }
              shell.activeMenu = newMenu;
            }
            if ((item != null) && item.isDisposed()) {
              item = null;
            }
          }
        }
      }
      if (item != null) {
        item.sendEvent(Arm);
      }
    }
    return null;
  }
}
