class PlaceHold {
  public int OnStartURIOpen(int aURI, boolean[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aURI, _retval);
  }
}
