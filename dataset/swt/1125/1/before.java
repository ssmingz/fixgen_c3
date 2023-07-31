class PlaceHold {
  public int SetWebBrowser(int aWebBrowser) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), aWebBrowser);
  }
}
