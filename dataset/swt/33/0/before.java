class PlaceHold {
  void update() {
    if (OS.IsPPC || OS.IsSP) {
      return;
    }
    if (OS.IsHPC) {
      Menu menuBar = parent.menuBar;
      if (menuBar != null) {
        Menu menu = this;
        while ((menu != null) && (menu != menuBar)) {
          menu = menu.getParentMenu();
        }
        if (menu == menuBar) {
          OS.CommandBar_DrawMenuBar(menuBar.hwndCB, 0);
          OS.CommandBar_Show(menuBar.hwndCB, true);
        }
      }
      return;
    }
    if (OS.IsWinCE) {
      return;
    }
    if ((style & SWT.BAR) != 0) {
      if (this == parent.menuBar) {
        OS.DrawMenuBar(parent.handle);
      }
      return;
    }
    if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) < ((4 << 16) | 10)) {
      return;
    }
    boolean hasCheck = false;
    boolean hasImage = false;
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      if (item.image != null) {
        if ((hasImage = true) && hasCheck) {
          break;
        }
      }
      if ((item.style & (SWT.CHECK | SWT.RADIO)) != 0) {
        if ((hasCheck = true) && hasImage) {
          break;
        }
      }
    }
    if (!OS.IsWin95) {
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      info.fMask = OS.MIIM_BITMAP;
      for (int i = 0; i < items.length; i++) {
        MenuItem item = items[i];
        if ((style & SWT.SEPARATOR) == 0) {
          if (item.image == null) {
            info.hbmpItem = (hasImage) ? OS.HBMMENU_CALLBACK : 0;
            OS.SetMenuItemInfo(handle, item.id, false, info);
          }
        }
      }
    }
    MENUINFO lpcmi = new MENUINFO();
    lpcmi.cbSize = MENUINFO.sizeof;
    lpcmi.fMask = OS.MIM_STYLE;
    OS.GetMenuInfo(handle, lpcmi);
    if (hasImage && (!hasCheck)) {
      lpcmi.dwStyle |= OS.MNS_CHECKORBMP;
    } else {
      lpcmi.dwStyle &= ~OS.MNS_CHECKORBMP;
    }
    OS.SetMenuInfo(handle, lpcmi);
  }
}
