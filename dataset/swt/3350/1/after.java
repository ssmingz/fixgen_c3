class PlaceHold {
  public Menu getSystemMenu() {
    checkDevice();
    if (appMenu == null) {
      NSMenu mainMenu = NSApplication.sharedApplication().mainMenu();
      NSMenu nsAppMenu = mainMenu.itemAtIndex(0).submenu();
      appMenu = new Menu(this, nsAppMenu);
      long nsCount = nsAppMenu.numberOfItems();
      for (int j = 0; j < nsCount; j++) {
        NSMenuItem currMenuItem = nsAppMenu.itemAtIndex(j);
        new MenuItem(appMenu, currMenuItem);
      }
    }
    return appMenu;
  }
}
