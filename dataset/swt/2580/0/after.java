class PlaceHold {
  public int GetProtocolHandler(byte[] aScheme, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aScheme, _retval);
  }
}
