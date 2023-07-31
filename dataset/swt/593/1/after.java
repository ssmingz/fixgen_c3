class PlaceHold {
  public int IsCommandEnabled(byte[] command, boolean[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), command, _retval);
  }
}
