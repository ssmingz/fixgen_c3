class PlaceHold {
  int GetWebBrowser(int aWebBrowser) {
    int[] ret = new int[1];
    if (webBrowser != null) {
      webBrowser.AddRef();
      ret[0] = webBrowser.getAddress();
    }
    XPCOM.memmove(aWebBrowser, ret, 4);
    return XPCOM.NS_OK;
  }
}
