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
    menuBar = menu;
    int hMenu = 0;
    if (menuBar != null) {
      hMenu = menuBar.handle;
    }
    OS.SetMenu(handle, hMenu);
    destroyAcceleratorTable();
  }
}
