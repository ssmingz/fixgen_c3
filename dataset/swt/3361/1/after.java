class PlaceHold {
  public int Available(int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), _retval);
  }
}
