class PlaceHold {
  public void refresh() {
    if (awaitingNavigate) {
      return;
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
    rc = webNavigation.Reload(LOAD_FLAGS_NONE);
    webNavigation.Release();
    if (rc == XPCOM.NS_OK) {
      return;
    }
    if ((rc != XPCOM.NS_ERROR_INVALID_POINTER) && (rc != XPCOM.NS_ERROR_FILE_NOT_FOUND)) {
      error(rc);
    }
  }
}
