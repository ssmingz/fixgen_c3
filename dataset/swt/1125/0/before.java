class PlaceHold {
  public int GetWebBrowser(int[] aWebBrowser) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), aWebBrowser);
  }
}
