class PlaceHold {
  public String getUrl() {
    long[] result = new long[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_24_IID, result);
    if (rc != XPCOM.NS_OK) {
      rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    }
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    long[] aCurrentURI = new long[1];
    rc = webNavigation.GetCurrentURI(aCurrentURI);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    webNavigation.Release();
    byte[] dest = null;
    if (aCurrentURI[0] != 0) {
      nsIURI uri = new nsIURI(aCurrentURI[0]);
      long aSpec = XPCOM.nsEmbedCString_new();
      rc = uri.GetSpec(aSpec);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      int length = XPCOM.nsEmbedCString_Length(aSpec);
      long buffer = XPCOM.nsEmbedCString_get(aSpec);
      dest = new byte[length];
      XPCOM.memmove(dest, buffer, length);
      XPCOM.nsEmbedCString_delete(aSpec);
      uri.Release();
    }
    if (dest == null) {
      return "";
    }
    String location = new String(dest);
    if (location.equals(URI_FILEROOT)) {
      location = ABOUT_BLANK;
    } else {
      int length = URI_FILEROOT.length();
      if (location.startsWith(URI_FILEROOT) && (location.charAt(length) == '#')) {
        location = ABOUT_BLANK + location.substring(length);
      }
    }
    return location;
  }
}
