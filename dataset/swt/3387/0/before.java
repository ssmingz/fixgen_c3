class PlaceHold {
  public boolean setText(String html, boolean trusted) {
    if (browser != browser.getDisplay().getFocusControl()) {
      Deactivate();
    }
    byte[] data = null;
    try {
      data = html.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      return false;
    }
    delegate.removeWindowSubclass();
    long[] result = new long[1];
    int rc = webBrowser.QueryInterface(NS_IWEBBROWSERSTREAM_IID, result);
    if ((rc == XPCOM.NS_OK) && (result[0] != 0)) {
      new nsISupports(result[0]).Release();
      result[0] = 0;
      boolean blankLoading = htmlBytes != null;
      htmlBytes = data;
      untrustedText = !trusted;
      if (blankLoading) {
        return true;
      }
      rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_ERROR_NO_INTERFACE);
      }
      nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
      result[0] = 0;
      char[] uri = new char[ABOUT_BLANK.length() + 1];
      ABOUT_BLANK.getChars(0, ABOUT_BLANK.length(), uri, 0);
      rc = webNavigation.LoadURI(uri, LOAD_FLAGS_NONE, 0, 0, 0);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      webNavigation.Release();
    } else {
      byte[] contentCharsetBuffer = MozillaDelegate.wcsToMbcs(null, "UTF-8", false);
      long aContentCharset =
          XPCOM.nsEmbedCString_new(contentCharsetBuffer, contentCharsetBuffer.length);
      byte[] contentTypeBuffer = MozillaDelegate.wcsToMbcs(null, "text/html", false);
      long aContentType = XPCOM.nsEmbedCString_new(contentTypeBuffer, contentTypeBuffer.length);
      rc = XPCOM.NS_GetServiceManager(result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIServiceManager serviceManager = new nsIServiceManager(result[0]);
      result[0] = 0;
      rc = serviceManager.GetService(NS_IOSERVICE_CID, NS_IIOSERVICE_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIIOService ioService = new nsIIOService(result[0]);
      result[0] = 0;
      byte[] aString;
      if (trusted) {
        aString = MozillaDelegate.wcsToMbcs(null, URI_FILEROOT, false);
      } else {
        aString = MozillaDelegate.wcsToMbcs(null, ABOUT_BLANK, false);
      }
      long aSpec = XPCOM.nsEmbedCString_new(aString, aString.length);
      rc = ioService.NewURI(aSpec, null, 0, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      XPCOM.nsEmbedCString_delete(aSpec);
      ioService.Release();
      nsIURI uri = new nsIURI(result[0]);
      result[0] = 0;
      rc = webBrowser.QueryInterface(NS_IINTERFACEREQUESTOR_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_ERROR_NO_INTERFACE);
      }
      nsIInterfaceRequestor interfaceRequestor = new nsIInterfaceRequestor(result[0]);
      result[0] = 0;
      InputStream inputStream = new InputStream(data);
      inputStream.AddRef();
      rc = interfaceRequestor.GetInterface(NS_IDOCSHELL_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_ERROR_NO_INTERFACE);
      }
      nsIDocShell docShell = new nsIDocShell(result[0]);
      result[0] = 0;
      rc =
          docShell.LoadStream(
              inputStream.getAddress(), uri.getAddress(), aContentType, aContentCharset, 0);
      docShell.Release();
      inputStream.Release();
      interfaceRequestor.Release();
      uri.Release();
      XPCOM.nsEmbedCString_delete(aContentType);
      XPCOM.nsEmbedCString_delete(aContentCharset);
    }
    return true;
  }
}
