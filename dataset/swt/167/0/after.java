class PlaceHold {
  void initSpinup(nsIComponentManager componentManager) {
    if (MozillaDelegate.needsSpinup()) {
      long[] result = new long[1];
      int rc = componentManager.CreateInstance(NS_APPSHELL_CID, 0, NS_IAPPSHELL_IID, result);
      if (rc != XPCOM.NS_ERROR_NO_INTERFACE) {
        if (rc != XPCOM.NS_OK) {
          browser.dispose();
          error(rc);
        }
        if (result[0] == 0) {
          browser.dispose();
          error(NS_NOINTERFACE);
        }
        AppShell = new nsIAppShell(result[0]);
        result[0] = 0;
        rc = AppShell.Create(0, null);
        if (rc != XPCOM.NS_OK) {
          browser.dispose();
          error(rc);
        }
        rc = AppShell.Spinup();
        if (rc != XPCOM.NS_OK) {
          browser.dispose();
          error(rc);
        }
      }
    }
  }
}
