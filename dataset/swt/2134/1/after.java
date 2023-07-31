class PlaceHold {
  LRESULT WM_INITMENUPOPUP(int wParam, int lParam) {
    if (display.accelKeyHit) {
      return null;
    }
    Shell shell = getShell();
    Menu oldMenu = shell.activeMenu;
    Menu newMenu = null;
    if (OS.HIWORD(lParam) == 0) {
      newMenu = menuShell().findMenu(wParam);
      if (newMenu != null) {
        newMenu.update();
      }
    }
    Menu menu = newMenu;
    while ((menu != null) && (menu != oldMenu)) {
      menu = menu.getParentMenu();
    }
    if (menu == null) {
      menu = shell.activeMenu;
      while (menu != null) {
        menu.sendEvent(Hide);
        if (menu.isDisposed()) {
          break;
        }
        menu = menu.getParentMenu();
        Menu ancestor = newMenu;
        while ((ancestor != null) && (ancestor != menu)) {
          ancestor = ancestor.getParentMenu();
        }
        if (ancestor != null) {
          break;
        }
      }
    }
    if ((newMenu != null) && newMenu.isDisposed()) {
      newMenu = null;
    }
    shell.activeMenu = newMenu;
    if ((newMenu != null) && (newMenu != oldMenu)) {
      newMenu.sendEvent(Show);
    }
    return null;
  }
}
