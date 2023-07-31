class PlaceHold {
  public int GetFileXPref(byte[] pref, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 41, getAddress(), pref, _retval);
  }
}
