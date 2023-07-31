class PlaceHold {
  public void setMenu(Menu menu) {
    checkWidget();
    if (menu != null) {
      if ((menu.style & SWT.POP_UP) == 0) {
        error(ERROR_MENU_NOT_POP_UP);
      }
      if (menu.parent != menuShell()) {
        error(ERROR_INVALID_PARENT);
      }
    }
    this.menu = menu;
  }
}
