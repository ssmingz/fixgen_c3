class PlaceHold {
  static void LoadLibraries() {
    boolean initLoaded = false;
    if (Boolean.getBoolean(GRE_INITIALIZED)) {
      Initialized = true;
    }
    MozillaPath = System.getProperty(XULRUNNER_PATH);
    if (MozillaPath == null) {
      try {
        Class.forName("org.eclipse.swt.browser.XULRunnerInitializer");
        MozillaPath = System.getProperty(XULRUNNER_PATH);
      } catch (ClassNotFoundException e) {
      }
    }
    if (MozillaPath == null) {
      try {
        String libName = MozillaDelegate.getSWTInitLibraryName();
        Library.loadLibrary(libName);
        initLoaded = true;
      } catch (UnsatisfiedLinkError e) {
      }
    } else {
      if (SEPARATOR_OS == '/') {
        MozillaPath = MozillaPath.replace('\\', SEPARATOR_OS);
      } else {
        MozillaPath = MozillaPath.replace('/', SEPARATOR_OS);
      }
      MozillaPath += SEPARATOR_OS + MozillaDelegate.getLibraryName();
      IsXULRunner = true;
    }
    if (initLoaded) {
      MozillaPath = InitDiscoverXULRunner();
      IsXULRunner = MozillaPath.length() > 0;
      if (IsXULRunner) {
        byte[] bytes = MozillaDelegate.wcsToMbcs(null, MozillaPath, true);
        int rc = XPCOMInit.XPCOMGlueStartup(bytes);
        if (rc != XPCOM.NS_OK) {
          MozillaPath = MozillaPath.substring(0, MozillaPath.lastIndexOf(SEPARATOR_OS));
          if (Device.DEBUG) {
            System.out.println("cannot use detected XULRunner: " + MozillaPath);
          }
          long ptr = C.getenv(MozillaDelegate.wcsToMbcs(null, MOZILLA_FIVE_HOME, true));
          if (ptr == 0) {
            IsXULRunner = false;
          } else {
            int length = C.strlen(ptr);
            bytes = new byte[length];
            C.memmove(bytes, ptr, length);
            MozillaPath = new String(MozillaDelegate.mbcsToWcs(null, bytes));
            if (MozillaPath.indexOf("xulrunner") == (-1)) {
              IsXULRunner = false;
            } else {
              MozillaPath += SEPARATOR_OS + MozillaDelegate.getLibraryName();
              bytes = MozillaDelegate.wcsToMbcs(null, MozillaPath, true);
              rc = XPCOMInit.XPCOMGlueStartup(bytes);
              if (rc == XPCOM.NS_OK) {
                if (SEPARATOR_OS == '/') {
                  MozillaPath = MozillaPath.replace('\\', SEPARATOR_OS);
                } else {
                  MozillaPath = MozillaPath.replace('/', SEPARATOR_OS);
                }
              } else {
                IsXULRunner = false;
                MozillaPath = MozillaPath.substring(0, MozillaPath.lastIndexOf(SEPARATOR_OS));
                if (Device.DEBUG) {
                  System.out.println("failed to start as XULRunner: " + MozillaPath);
                }
              }
            }
          }
        }
        if (IsXULRunner) {
          XPCOMInitWasGlued = true;
        }
      }
    }
  }
}
