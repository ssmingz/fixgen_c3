class PlaceHold {
  int IsPreferred(int aContentType, int aDesiredContentType, int retval) {
    boolean preferred = false;
    int size = XPCOM.strlen(aContentType);
    if (size > 0) {
      byte[] typeBytes = new byte[size + 1];
      XPCOM.memmove(typeBytes, aContentType, size);
      String contentType = new String(typeBytes, 0, size);
      if ((!contentType.equals(CONTENT_MAYBETEXT)) && (!contentType.equals(CONTENT_MULTIPART))) {
        int[] result = new int[1];
        int rc = XPCOM.NS_GetServiceManager(result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_NOINTERFACE);
        }
        nsIServiceManager serviceManager = new nsIServiceManager(result[0]);
        result[0] = 0;
        byte[] aContractID = MozillaDelegate.wcsToMbcs(null, NS_WEBNAVIGATIONINFO_CONTRACTID, true);
        rc = serviceManager.GetServiceByContractID(aContractID, NS_IWEBNAVIGATIONINFO_IID, result);
        if (rc == XPCOM.NS_OK) {
          byte[] bytes = MozillaDelegate.wcsToMbcs(null, contentType, true);
          int typePtr = XPCOM.nsEmbedCString_new(bytes, bytes.length);
          nsIWebNavigationInfo info = new nsIWebNavigationInfo(result[0]);
          result[0] = 0;
          int[] isSupportedResult = new int[1];
          rc = info.IsTypeSupported(typePtr, 0, isSupportedResult);
          if (rc != XPCOM.NS_OK) {
            error(rc);
          }
          info.Release();
          XPCOM.nsEmbedCString_delete(typePtr);
          preferred = isSupportedResult[0] != 0;
        } else {
          result[0] = 0;
          rc = serviceManager.GetService(NS_CATEGORYMANAGER_CID, NS_ICATEGORYMANAGER_IID, result);
          if (rc != XPCOM.NS_OK) {
            error(rc);
          }
          if (result[0] == 0) {
            error(NS_NOINTERFACE);
          }
          nsICategoryManager categoryManager = new nsICategoryManager(result[0]);
          result[0] = 0;
          byte[] categoryBytes = MozillaDelegate.wcsToMbcs(null, "Gecko-Content-Viewers", true);
          rc = categoryManager.GetCategoryEntry(categoryBytes, typeBytes, result);
          categoryManager.Release();
          preferred = rc == XPCOM.NS_OK;
        }
        serviceManager.Release();
      }
    }
    XPCOM.memmove(retval, new int[] {preferred ? 1 : 0}, 4);
    if (preferred) {
      XPCOM.memmove(aDesiredContentType, new int[] {0}, PTR_SIZEOF);
    }
    return XPCOM.NS_OK;
  }
}
