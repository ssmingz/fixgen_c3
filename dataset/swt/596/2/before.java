class PlaceHold {
  public void setMenu(Menu menu) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
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
