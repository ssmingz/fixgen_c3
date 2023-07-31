class PlaceHold {
  public int GetCanGoForward(boolean[] aCanGoForward) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aCanGoForward);
  }
}
