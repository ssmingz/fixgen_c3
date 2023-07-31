class PlaceHold {
  public int GetFileXPref(byte[] pref, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 41, getAddress(), pref, retVal);
  }
}
