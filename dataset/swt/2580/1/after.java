class PlaceHold {
  public int GetProtocolFlags(byte[] aScheme, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aScheme, _retval);
  }
}
