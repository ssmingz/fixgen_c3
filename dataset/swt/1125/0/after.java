class PlaceHold {
  public int GetWebBrowser(int[] aWebBrowser) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aWebBrowser);
  }
}
