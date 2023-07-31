class PlaceHold {
  public String getUrl() {
    if (awaitingNavigate) {
      return "";
    }
    int[] result = new int[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    int[] aCurrentURI = new int[1];
    rc = webNavigation.GetCurrentURI(aCurrentURI);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    webNavigation.Release();
    byte[] dest = null;
    if (aCurrentURI[0] != 0) {
      nsIURI uri = new nsIURI(aCurrentURI[0]);
      int aSpec = XPCOM.nsEmbedCString_new();
      rc = uri.GetSpec(aSpec);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      int length = XPCOM.nsEmbedCString_Length(aSpec);
      int buffer = XPCOM.nsEmbedCString_get(aSpec);
      dest = new byte[length];
      XPCOM.memmove(dest, buffer, length);
      XPCOM.nsEmbedCString_delete(aSpec);
      uri.Release();
    }
    if (dest == null) {
      return "";
    }
    String location = new String(dest);
    if (location.equals(URI_FROMMEMORY)) {
      location = ABOUT_BLANK;
    }
    return location;
  }
}
