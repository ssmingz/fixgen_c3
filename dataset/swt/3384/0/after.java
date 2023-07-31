class PlaceHold {
  void onDispose(Display display) {
    if ((!browser.isClosing) && (!browser.isDisposed())) {
      LocationListener[] oldLocationListeners = locationListeners;
      locationListeners = new LocationListener[0];
      ignoreAllMessages = true;
      execute("window.location.replace('about:blank');");
      ignoreAllMessages = false;
      locationListeners = oldLocationListeners;
    }
    if (badCertRequest != 0) {
      new nsISupports(badCertRequest).Release();
    }
    int rc =
        webBrowser.RemoveWebBrowserListener(
            weakReference.getAddress(), IIDStore.GetIID(nsIWebProgressListener.class));
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    rc = webBrowser.SetParentURIContentListener(0);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    rc = webBrowser.SetContainerWindow(0);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    unhookDOMListeners();
    long[] result = new long[1];
    rc = webBrowser.QueryInterface(IIDStore.GetIID(nsIBaseWindow.class), result);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
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
    webBrowser = null;
    webBrowserObject = null;
    lastNavigateURL = null;
    htmlBytes = null;
    listener = null;
    if ((tip != null) && (!tip.isDisposed())) {
      tip.dispose();
    }
    tip = null;
    location = size = null;
    for (LONG ptrObject : unhookedDOMWindows) {
      new nsISupports(ptrObject.value).Release();
    }
    unhookedDOMWindows = null;
    Enumeration<BrowserFunction> elements = functions.elements();
    while (elements.hasMoreElements()) {
      BrowserFunction function = elements.nextElement();
      AllFunctions.remove(new Integer(function.index));
      function.dispose(false);
    }
    functions = null;
    delegate.onDispose(embedHandle);
    delegate = null;
    embedHandle = 0;
    BrowserCount--;
  }
}
