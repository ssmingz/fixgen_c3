class PlaceHold {
  public int Load(long[] _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 8, getAddress(), _retval);
  }
}
