class PlaceHold {
  Browser getBrowser(int aDOMWindow) {
    if (aDOMWindow == 0) {
      return null;
    }
    int[] result = new int[1];
    int rc = XPCOM.NS_GetServiceManager(result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    nsIServiceManager serviceManager = new nsIServiceManager(result[0]);
    result[0] = 0;
    byte[] aContractID = MozillaDelegate.wcsToMbcs(null, NS_WINDOWWATCHER_CONTRACTID, true);
    rc = serviceManager.GetServiceByContractID(aContractID, NS_IWINDOWWATCHER_IID, result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    serviceManager.Release();
    nsIWindowWatcher windowWatcher = new nsIWindowWatcher(result[0]);
    result[0] = 0;
    nsIDOMWindow window = new nsIDOMWindow(aDOMWindow);
    rc = window.GetTop(result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    aDOMWindow = result[0];
    result[0] = 0;
    rc = windowWatcher.GetChromeForWindow(aDOMWindow, result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    windowWatcher.Release();
    nsIWebBrowserChrome webBrowserChrome = new nsIWebBrowserChrome(result[0]);
    result[0] = 0;
    rc = webBrowserChrome.QueryInterface(NS_IEMBEDDINGSITEWINDOW_IID, result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    webBrowserChrome.Release();
    nsIEmbeddingSiteWindow embeddingSiteWindow = new nsIEmbeddingSiteWindow(result[0]);
    result[0] = 0;
    rc = embeddingSiteWindow.GetSiteWindow(result);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_NOINTERFACE);
    }
    embeddingSiteWindow.Release();
    return Mozilla.findBrowser(result[0]);
  }
}
