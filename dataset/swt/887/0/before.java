class PlaceHold {
  public int AddWebBrowserListener(int aListener, nsID aIID) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), aListener, aIID);
  }
}
