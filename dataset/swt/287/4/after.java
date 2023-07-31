class PlaceHold {
  void initWindowCreator(nsIServiceManager serviceManager) {
    WindowCreator = new WindowCreator2();
    WindowCreator.AddRef();
    long[] result = new long[1];
    byte[] aContractID = MozillaDelegate.wcsToMbcs(null, NS_WINDOWWATCHER_CONTRACTID, true);
    int rc = serviceManager.GetServiceByContractID(aContractID, NS_IWINDOWWATCHER_IID, result);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    if (result[0] == 0) {
      browser.dispose();
      error(NS_NOINTERFACE);
    }
    nsIWindowWatcher windowWatcher = new nsIWindowWatcher(result[0]);
    result[0] = 0;
    rc = windowWatcher.SetWindowCreator(WindowCreator.getAddress());
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    windowWatcher.Release();
  }
}
