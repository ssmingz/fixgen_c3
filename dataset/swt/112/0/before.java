class PlaceHold {
  public int AddWebBrowserListener(long aListener, nsID aIID) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aListener, aIID);
  }
}
