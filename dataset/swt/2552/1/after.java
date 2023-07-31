class PlaceHold {
  public int GetCharPref(byte[] aPrefName, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 11, getAddress(), aPrefName, _retval);
  }
}
