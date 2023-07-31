class PlaceHold {
  public boolean back() {
    checkWidget();
    int[] result = new int[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    if (rc != XPCOM.NS_OK) {
      throw new SWTError(XPCOM.errorMsg(rc));
    }
    if (result[0] == 0) {
      throw new SWTError(XPCOM.errorMsg(NS_ERROR_NO_INTERFACE));
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    webNavigation.GoBack();
    webNavigation.Release();
    return rc == XPCOM.NS_OK;
  }
}
