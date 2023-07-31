class PlaceHold {
  void onDispose(Display display) {
    display.setData(ADD_WIDGET_KEY, new Object[] {new Integer(mozillaHandle), null});
    int rc =
        webBrowser.RemoveWebBrowserListener(
            weakReference.getAddress(), NS_IWEBPROGRESSLISTENER_IID);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    rc = webBrowser.SetParentURIContentListener(0);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    int[] result = new int[1];
    rc = webBrowser.QueryInterface(NS_IBASEWINDOW_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIBaseWindow baseWindow = new nsIBaseWindow(result[0]);
    rc = baseWindow.Destroy();
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    baseWindow.Release();
    Release();
    webBrowser.Release();
    if ((tip != null) && (!tip.isDisposed())) {
      tip.dispose();
    }
    tip = null;
    BrowserCount--;
  }
}
