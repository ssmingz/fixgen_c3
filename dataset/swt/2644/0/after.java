class PlaceHold {
  @Override
  long gtk_hide(long widget) {
    if ((style & SWT.POP_UP) != 0) {
      if (display.activeShell != null) {
        display.activeShell = getShell();
        activeShell.ignoreFocusOut = false;
      }
    }
    sendEvent(Hide);
    if (OS.ubuntu_menu_proxy_get() != 0) {
      MenuItem[] items = getItems();
      for (int i = 0; i < items.length; i++) {
        MenuItem item = items[i];
        if (item.updateAcceleratorText(false)) {
          continue;
        }
      }
    }
    return 0;
  }
}
