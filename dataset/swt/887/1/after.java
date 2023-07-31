class PlaceHold {
  public int RemoveWebBrowserListener(int aListener, nsID aIID) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aListener, aIID);
  }
}
