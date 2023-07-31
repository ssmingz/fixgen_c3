class PlaceHold {
  int ShowAsModal() {
    long[] result = new long[1];
    int rc = XPCOM.NS_GetServiceManager(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    nsIServiceManager serviceManager = new nsIServiceManager(result[0]);
    result[0] = 0;
    byte[] aContractID = MozillaDelegate.wcsToMbcs(null, NS_CONTEXTSTACK_CONTRACTID, true);
    rc = serviceManager.GetServiceByContractID(aContractID, NS_IJSCONTEXTSTACK_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    serviceManager.Release();
    nsIJSContextStack stack = new nsIJSContextStack(result[0]);
    result[0] = 0;
    rc = stack.Push(0);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    Shell shell = browser.getShell();
    Display display = browser.getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    rc = stack.Pop(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    stack.Release();
    return XPCOM.NS_OK;
  }
}
