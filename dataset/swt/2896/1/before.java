class PlaceHold {
  void createMainMenu() {
    NSString appName = getAppName();
    NSString emptyStr = NSString.stringWith("");
    NSMenu mainMenu = ((NSMenu) (new NSMenu().alloc()));
    mainMenu.initWithTitle(emptyStr);
    NSMenuItem menuItem;
    NSMenu appleMenu;
    NSString format = NSString.stringWith("%@ %@");
    NSString title;
    NSMenuItem appItem = menuItem = mainMenu.addItemWithTitle(emptyStr, 0, emptyStr);
    appleMenu = ((NSMenu) (new NSMenu().alloc()));
    appleMenu.initWithTitle(emptyStr);
    OS.objc_msgSend(application.id, OS.sel_registerName("setAppleMenu:"), appleMenu.id);
    title =
        new NSString(
            OS.objc_msgSend(
                class_NSString,
                sel_stringWithFormat_,
                format.id,
                NSString.stringWith(SWT.getMessage("About")).id,
                appName.id));
    menuItem = appleMenu.addItemWithTitle(title, sel_orderFrontStandardAboutPanel_, emptyStr);
    menuItem.setTarget(applicationDelegate);
    appleMenu.addItem(NSMenuItem.separatorItem());
    title = NSString.stringWith(SWT.getMessage("Preferences..."));
    menuItem = appleMenu.addItemWithTitle(title, 0, NSString.stringWith(","));
    appleMenu.addItem(NSMenuItem.separatorItem());
    title = NSString.stringWith(SWT.getMessage("Services"));
    menuItem = appleMenu.addItemWithTitle(title, 0, emptyStr);
    NSMenu servicesMenu = ((NSMenu) (new NSMenu().alloc()));
    servicesMenu.initWithTitle(emptyStr);
    appleMenu.setSubmenu(servicesMenu, menuItem);
    servicesMenu.release();
    application.setServicesMenu(servicesMenu);
    appleMenu.addItem(NSMenuItem.separatorItem());
    title =
        new NSString(
            OS.objc_msgSend(
                class_NSString,
                sel_stringWithFormat_,
                format.id,
                NSString.stringWith(SWT.getMessage("Hide")).id,
                appName.id));
    menuItem = appleMenu.addItemWithTitle(title, sel_hide_, NSString.stringWith("h"));
    menuItem.setTarget(applicationDelegate);
    title = NSString.stringWith(SWT.getMessage("Hide Others"));
    menuItem =
        appleMenu.addItemWithTitle(title, sel_hideOtherApplications_, NSString.stringWith("h"));
    menuItem.setKeyEquivalentModifierMask(OS.NSCommandKeyMask | OS.NSAlternateKeyMask);
    menuItem.setTarget(applicationDelegate);
    title = NSString.stringWith(SWT.getMessage("Show All"));
    menuItem = appleMenu.addItemWithTitle(title, sel_unhideAllApplications_, emptyStr);
    menuItem.setTarget(applicationDelegate);
    appleMenu.addItem(NSMenuItem.separatorItem());
    title =
        new NSString(
            OS.objc_msgSend(
                class_NSString,
                sel_stringWithFormat_,
                format.id,
                NSString.stringWith(SWT.getMessage("Quit")).id,
                appName.id));
    menuItem = appleMenu.addItemWithTitle(title, sel_quitRequested_, NSString.stringWith("q"));
    menuItem.setTarget(applicationDelegate);
    mainMenu.setSubmenu(appleMenu, appItem);
    appleMenu.release();
    application.setMainMenu(mainMenu);
    mainMenu.release();
  }
}
