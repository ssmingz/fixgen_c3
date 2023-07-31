class PlaceHold {
  public int Load(int[] _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 8, getAddress(), _retval);
  }
}
