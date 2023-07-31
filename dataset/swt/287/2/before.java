class PlaceHold {
  public void stop() {
    htmlBytes = null;
    int[] result = new int[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    rc = webNavigation.Stop(STOP_ALL);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    webNavigation.Release();
  }
}
