class PlaceHold {
  public int GetBoolPref(byte[] aPrefName, boolean[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 9, getAddress(), aPrefName, _retval);
  }
}
