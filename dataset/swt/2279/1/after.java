class PlaceHold {
  public void setMenuBar(Menu menu) {
    checkWidget();
    if (menuBar == menu) {
      return;
    }
    if (menu != null) {
      if (menu.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if ((menu.style & SWT.BAR) == 0) {
        error(ERROR_MENU_NOT_BAR);
      }
      if (menu.parent != this) {
        error(ERROR_INVALID_PARENT);
      }
    }
    if (OS.IsWinCE) {
      if (OS.IsHPC) {
        boolean resize = menuBar != menu;
        if (menuBar != null) {
          OS.CommandBar_Show(menuBar.hwndCB, false);
        }
        menuBar = menu;
        if (menuBar != null) {
          OS.CommandBar_Show(menuBar.hwndCB, true);
        }
        if (resize) {
          sendEvent(Resize);
          if (isDisposed()) {
            return;
          }
          if (layout != null) {
            markLayout(false, false);
            updateLayout(true, false);
          }
        }
      } else {
        if (OS.IsPPC) {
          boolean resize = getMaximized() && (menuBar != menu);
          if (menuBar != null) {
            OS.ShowWindow(menuBar.hwndCB, SW_HIDE);
          }
          menuBar = menu;
          if (menuBar != null) {
            OS.ShowWindow(menuBar.hwndCB, SW_SHOW);
          }
          if (resize) {
            _setMaximized(true);
          }
        }
        if (OS.IsSP) {
          if (menuBar != null) {
            OS.ShowWindow(menuBar.hwndCB, SW_HIDE);
          }
          menuBar = menu;
          if (menuBar != null) {
            OS.ShowWindow(menuBar.hwndCB, SW_SHOW);
          }
        }
      }
    } else {
      if (menu != null) {
        display.removeBar(menu);
      }
      menuBar = menu;
      long hMenu = (menuBar != null) ? menuBar.handle : 0;
      OS.SetMenu(handle, hMenu);
    }
    destroyAccelerators();
  }
}
