class PlaceHold {
  boolean sendChangingEvent(String url) {
    isViewingErrorPage = url.indexOf("netError.xhtml") != (-1);
    boolean doit = true;
    if (request == 0) {
      if (!url.startsWith(PREFIX_JAVASCRIPT)) {
        if (locationListeners.length > 0) {
          LocationEvent event = new LocationEvent(browser);
          event.display = browser.getDisplay();
          event.widget = browser;
          event.location = url;
          if (event.location.equals(URI_FILEROOT)) {
            event.location = ABOUT_BLANK;
          } else {
            int length = URI_FILEROOT.length();
            if (event.location.startsWith(URI_FILEROOT) && (event.location.charAt(length) == '#')) {
              event.location = ABOUT_BLANK + event.location.substring(length);
            }
          }
          event.doit = doit;
          for (int i = 0; i < locationListeners.length; i++) {
            locationListeners[i].changing(event);
          }
          doit = event.doit && (!browser.isDisposed());
        }
        if (doit) {
          if (jsEnabled != jsEnabledOnNextPage) {
            jsEnabled = jsEnabledOnNextPage;
            long[] result = new long[1];
            int rc = webBrowser.QueryInterface(NS_IWEBBROWSERSETUP_IID, result);
            if (rc != XPCOM.NS_OK) {
              error(rc);
            }
            if (result[0] == 0) {
              error(NS_NOINTERFACE);
            }
            nsIWebBrowserSetup setup = new nsIWebBrowserSetup(result[0]);
            result[0] = 0;
            rc = setup.SetProperty(SETUP_ALLOW_JAVASCRIPT, jsEnabled ? 1 : 0);
            if (rc != XPCOM.NS_OK) {
              error(rc);
            }
            setup.Release();
          }
          if (!isViewingErrorPage) {
            lastNavigateURL = url;
          }
        }
      }
    }
    return doit;
  }
}
