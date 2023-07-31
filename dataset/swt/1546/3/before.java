class PlaceHold {
  int OnLocationChange(int aWebProgress, int aRequest, int aLocation) {
    if ((request != 0) && (request != aRequest)) {
      request = aRequest;
    }
    if (locationListeners.length == 0) {
      return XPCOM.NS_OK;
    }
    nsIWebProgress webProgress = new nsIWebProgress(aWebProgress);
    int[] aDOMWindow = new int[1];
    int rc = webProgress.GetDOMWindow(aDOMWindow);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (aDOMWindow[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIDOMWindow domWindow = new nsIDOMWindow(aDOMWindow[0]);
    int[] aTop = new int[1];
    rc = domWindow.GetTop(aTop);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (aTop[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    domWindow.Release();
    nsIDOMWindow topWindow = new nsIDOMWindow(aTop[0]);
    topWindow.Release();
    nsIURI location = new nsIURI(aLocation);
    int aSpec = XPCOM.nsEmbedCString_new();
    location.GetSpec(aSpec);
    int length = XPCOM.nsEmbedCString_Length(aSpec);
    int buffer = XPCOM.nsEmbedCString_get(aSpec);
    byte[] dest = new byte[length];
    XPCOM.memmove(dest, buffer, length);
    XPCOM.nsEmbedCString_delete(aSpec);
    String url = new String(dest);
    if (((!IsPre_1_8) && (aRequest == 0)) && url.startsWith(ABOUT_BLANK)) {
      return XPCOM.NS_OK;
    }
    LocationEvent event = new LocationEvent(browser);
    event.display = browser.getDisplay();
    event.widget = browser;
    event.location = url;
    if (event.location.equals(URI_FROMMEMORY)) {
      event.location = ABOUT_BLANK;
    }
    event.top = aTop[0] == aDOMWindow[0];
    for (int i = 0; i < locationListeners.length; i++) {
      locationListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}
