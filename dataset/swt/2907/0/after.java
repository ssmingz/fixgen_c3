class PlaceHold {
  int OnStartURIOpen(int aURI, int retval) {
    if (awaitingNavigate || (locationListeners.length == 0)) {
      XPCOM.memmove(retval, new int[] {0}, 4);
      return XPCOM.NS_OK;
    }
    nsIURI location = new nsIURI(aURI);
    int aSpec = XPCOM.nsEmbedCString_new();
    location.GetSpec(aSpec);
    int length = XPCOM.nsEmbedCString_Length(aSpec);
    int buffer = XPCOM.nsEmbedCString_get(aSpec);
    buffer = XPCOM.nsEmbedCString_get(aSpec);
    byte[] dest = new byte[length];
    XPCOM.memmove(dest, buffer, length);
    XPCOM.nsEmbedCString_delete(aSpec);
    String value = new String(dest);
    boolean doit = true;
    if (request == 0) {
      if (!value.startsWith(PREFIX_JAVASCRIPT)) {
        LocationEvent event = new LocationEvent(browser);
        event.display = browser.getDisplay();
        event.widget = browser;
        event.location = value;
        if (event.location.equals(URI_FROMMEMORY)) {
          event.location = ABOUT_BLANK;
        }
        event.doit = doit;
        for (int i = 0; i < locationListeners.length; i++) {
          locationListeners[i].changing(event);
        }
        doit = event.doit;
      }
    }
    XPCOM.memmove(retval, new int[] {doit ? 0 : 1}, 4);
    return XPCOM.NS_OK;
  }
}
