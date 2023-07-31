class PlaceHold {
  void initProfile(nsIServiceManager serviceManager, boolean isXULRunner) {
    long[] result = new long[1];
    byte[] buffer = MozillaDelegate.wcsToMbcs(null, NS_OBSERVER_CONTRACTID, true);
    int rc = serviceManager.GetServiceByContractID(buffer, NS_IOBSERVERSERVICE_IID, result);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    if (result[0] == 0) {
      browser.dispose();
      error(NS_NOINTERFACE);
    }
    nsIObserverService observerService = new nsIObserverService(result[0]);
    result[0] = 0;
    buffer = MozillaDelegate.wcsToMbcs(null, PROFILE_DO_CHANGE, true);
    int length = STARTUP.length();
    char[] chars = new char[length + 1];
    STARTUP.getChars(0, length, chars, 0);
    rc = observerService.NotifyObservers(0, buffer, chars);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    buffer = MozillaDelegate.wcsToMbcs(null, PROFILE_AFTER_CHANGE, true);
    rc = observerService.NotifyObservers(0, buffer, chars);
    if (rc != XPCOM.NS_OK) {
      browser.dispose();
      error(rc);
    }
    observerService.Release();
    if (isXULRunner) {
      int size = XPCOM.nsDynamicFunctionLoad_sizeof();
      long ptr = C.malloc(size * 2);
      C.memset(ptr, 0, size * 2);
      nsDynamicFunctionLoad functionLoad = new nsDynamicFunctionLoad();
      byte[] bytes = MozillaDelegate.wcsToMbcs(null, "XRE_NotifyProfile", true);
      functionLoad.functionName = C.malloc(bytes.length);
      C.memmove(functionLoad.functionName, bytes, bytes.length);
      functionLoad.function = C.malloc(PTR_SIZEOF);
      C.memmove(functionLoad.function, new long[] {0}, PTR_SIZEOF);
      XPCOM.memmove(ptr, functionLoad, XPCOM.nsDynamicFunctionLoad_sizeof());
      XPCOM.XPCOMGlueLoadXULFunctions(ptr);
      C.memmove(result, functionLoad.function, PTR_SIZEOF);
      long functionPtr = result[0];
      result[0] = 0;
      C.free(functionLoad.function);
      C.free(functionLoad.functionName);
      C.free(ptr);
      if (functionPtr != 0) {
        rc = XPCOM.Call(functionPtr);
        if (rc != XPCOM.NS_OK) {
          browser.dispose();
          error(rc);
        }
      }
    }
  }
}
