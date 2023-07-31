class PlaceHold {
  @Override
  long gtk_show(long widget) {
    if ((style & SWT.POP_UP) != 0) {
      if (display.activeShell != null) {
        display.activeShell = getShell();
        activeShell.ignoreFocusOut = true;
      }
      return 0;
    }
    sendEvent(Show);
    if (OS.ubuntu_menu_proxy_get() != 0) {
      MenuItem[] items = getItems();
      for (int i = 0; i < items.length; i++) {
        MenuItem item = items[i];
        if (item.updateAcceleratorText(true)) {
          continue;
        }
      }
    }
    return 0;
  }
}
