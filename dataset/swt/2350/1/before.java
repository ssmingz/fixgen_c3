class PlaceHold {
  public boolean setUrl(String url) {
    awaitingNavigate = false;
    int[] result = new int[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    char[] uri = new char[url.length() + 1];
    url.getChars(0, url.length(), uri, 0);
    rc = webNavigation.LoadURI(uri, LOAD_FLAGS_NONE, 0, 0, 0);
    webNavigation.Release();
    return rc == XPCOM.NS_OK;
  }
}
