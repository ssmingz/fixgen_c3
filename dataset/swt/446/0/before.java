class PlaceHold {
  void applicationWillFinishLaunching(int id, int sel, int notification) {
    boolean loaded = false;
    NSBundle bundle = NSBundle.bundleWithIdentifier(NSString.stringWith("com.apple.JavaVM"));
    NSDictionary dict =
        NSDictionary.dictionaryWithObject(applicationDelegate, NSString.stringWith("NSOwner"));
    NSString path =
        bundle.pathForResource(NSString.stringWith("DefaultApp"), NSString.stringWith("nib"));
    if (!loaded) {
      loaded = (path != null) && NSBundle.loadNibFile(path, dict, 0);
    }
    if (!loaded) {
      NSString resourcePath = bundle.resourcePath();
      path =
          (resourcePath != null)
              ? resourcePath.stringByAppendingString(
                  NSString.stringWith("/English.lproj/DefaultApp.nib"))
              : null;
      loaded = (path != null) && NSBundle.loadNibFile(path, dict, 0);
    }
    if (!loaded) {
      path =
          NSString.stringWith(
              System.getProperty("java.home") + "/../Resources/English.lproj/DefaultApp.nib");
      loaded = (path != null) && NSBundle.loadNibFile(path, dict, 0);
    }
    if (!loaded) {
      createMainMenu();
    }
    NSMenu mainmenu = application.mainMenu();
    NSMenuItem appitem = mainmenu.itemAtIndex(0);
    if (appitem != null) {
      NSString name = getAppName();
      NSString match = NSString.stringWith("%@");
      appitem.setTitle(name);
      NSMenu sm = appitem.submenu();
      NSArray ia = sm.itemArray();
      for (int i = 0; i < ia.count(); i++) {
        NSMenuItem ni = new NSMenuItem(ia.objectAtIndex(i));
        NSString title = ni.title().stringByReplacingOccurrencesOfString(match, name);
        ni.setTitle(title);
      }
      int quitIndex = sm.indexOfItemWithTarget(applicationDelegate, sel_terminate_);
      if (quitIndex != (-1)) {
        NSMenuItem quitItem = sm.itemAtIndex(quitIndex);
        quitItem.setAction(sel_applicationShouldTerminate_);
      }
    }
  }
}
