class PlaceHold {
  String initMozilla(String mozillaPath) {
    long ptr = C.getenv(MozillaDelegate.wcsToMbcs(null, MOZILLA_FIVE_HOME, true));
    if (ptr != 0) {
      int length = C.strlen(ptr);
      byte[] buffer = new byte[length];
      C.memmove(buffer, ptr, length);
      mozillaPath = new String(MozillaDelegate.mbcsToWcs(null, buffer));
      if (SEPARATOR_OS == '/') {
        mozillaPath = mozillaPath.replace('\\', SEPARATOR_OS);
      } else {
        mozillaPath = mozillaPath.replace('/', SEPARATOR_OS);
      }
    } else {
      browser.dispose();
      SWT.error(ERROR_NO_HANDLES, null, " [Unknown Mozilla path (MOZILLA_FIVE_HOME not set)]");
    }
    if (Device.DEBUG) {
      System.out.println("Mozilla path: " + mozillaPath);
    }
    if (Compatibility.fileExists(mozillaPath, "components/libwidget_gtk.so")) {
      browser.dispose();
      SWT.error(ERROR_NO_HANDLES, null, " [Mozilla GTK2 required (GTK1.2 detected)]");
    }
    try {
      Library.loadLibrary("swt-mozilla");
    } catch (UnsatisfiedLinkError e) {
      try {
        Library.loadLibrary("swt-mozilla-gcc3");
      } catch (UnsatisfiedLinkError ex) {
        browser.dispose();
        SWT.error(ERROR_NO_HANDLES, e, (" [MOZILLA_FIVE_HOME='" + mozillaPath) + "']");
      }
    }
    return mozillaPath;
  }
}
