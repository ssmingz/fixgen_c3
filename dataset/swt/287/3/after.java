class PlaceHold {
  void initWebBrowserWindows() {
    int rc = webBrowser.SetContainerWindow(webBrowserChrome.getAddress());
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    long[] result = new long[1];
    rc = webBrowser.QueryInterface(NS_IBASEWINDOW_10_IID, result);
    if (rc != XPCOM.NS_OK) {
      rc = webBrowser.QueryInterface(NS_IBASEWINDOW_IID, result);
      if (rc != XPCOM.NS_OK) {
        browser.dispose();
        error(rc);
      }
    }
    if (result[0] == 0) {
      browser.dispose();
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIBaseWindow baseWindow = new nsIBaseWindow(result[0]);
    result[0] = 0;
    Rectangle rect = browser.getClientArea();
    if (rect.isEmpty()) {
      rect.width = 1;
      rect.height = 1;
    }
    embedHandle = delegate.getHandle();
    rc = baseWindow.InitWindow(embedHandle, 0, 0, 0, rect.width, rect.height);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(NS_ERROR_FAILURE);
    }
    rc = delegate.createBaseWindow(baseWindow);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(NS_ERROR_FAILURE);
    }
    rc = baseWindow.SetVisibility(1);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(NS_ERROR_FAILURE);
    }
    baseWindow.Release();
  }
}
