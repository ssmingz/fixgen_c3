class PlaceHold {
  public int RemoveWebBrowserListener(long aListener, nsID aIID) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aListener, aIID);
  }
}
