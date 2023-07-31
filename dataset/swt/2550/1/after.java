class PlaceHold {
  void Activate() {
    isActive = true;
    long[] result = new long[1];
    int rc = webBrowser.QueryInterface(IIDStore.GetIID(nsIWebBrowserFocus.class), result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebBrowserFocus webBrowserFocus = new nsIWebBrowserFocus(result[0]);
    rc = webBrowserFocus.Activate();
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    webBrowserFocus.Release();
  }
}
