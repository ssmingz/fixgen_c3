class PlaceHold {
  public int SetWebBrowser(int aWebBrowser) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, getAddress(), aWebBrowser);
  }
}
