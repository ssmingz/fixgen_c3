class PlaceHold {
  public int GetCanGoBack(boolean[] aCanGoBack) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aCanGoBack);
  }
}
