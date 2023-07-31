class PlaceHold {
  public int GetIntPref(byte[] aPrefName, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 13, getAddress(), aPrefName, _retval);
  }
}
