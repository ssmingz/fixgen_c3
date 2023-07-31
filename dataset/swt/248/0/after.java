class PlaceHold {
  void updateQuitMenu() {
    if (isEmbedded) {
      return;
    }
    boolean enabled = true;
    Shell[] shells = getShells();
    int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    for (int i = 0; i < shells.length; i++) {
      Shell shell = shells[i];
      if (((shell.style & mask) != 0) && shell.isVisible()) {
        enabled = false;
        break;
      }
    }
    NSMenu mainmenu = application.mainMenu();
    NSMenuItem appitem = mainmenu.itemAtIndex(0);
    if (appitem != null) {
      NSMenu sm = appitem.submenu();
      int quitIndex =
          sm.indexOfItemWithTarget(applicationDelegate, sel_applicationShouldTerminate_);
      if (quitIndex != (-1)) {
        NSMenuItem quitItem = sm.itemAtIndex(quitIndex);
        quitItem.setEnabled(enabled);
      }
    }
  }
}
