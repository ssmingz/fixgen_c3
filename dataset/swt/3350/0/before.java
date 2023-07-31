class PlaceHold {
  void setMenuBar(Menu menu) {
    if (menu == null) {
      menu = appMenuBar;
    }
    if (menu == menuBar) {
      return;
    }
    menuBar = menu;
    NSMenu menubar = application.mainMenu();
    OS.CancelMenuTracking(OS.AcquireRootMenu(), true, 0);
    int count = menubar.numberOfItems();
    while (count > 1) {
      menubar.removeItemAtIndex(count - 1);
      count--;
    }
    if (menu != null) {
      MenuItem[] items = menu.getItems();
      for (int i = 0; i < items.length; i++) {
        MenuItem item = items[i];
        NSMenuItem nsItem = item.nsItem;
        nsItem.setMenu(null);
        menubar.addItem(nsItem);
        boolean enabled = menu.getEnabled() && item.getEnabled();
        nsItem.setEnabled(!enabled);
        nsItem.setEnabled(enabled);
      }
    }
  }
}
