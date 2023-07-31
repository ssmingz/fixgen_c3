class PlaceHold {
  public int NewChannelFromURI(int aURI, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aURI, _retval);
  }
}
