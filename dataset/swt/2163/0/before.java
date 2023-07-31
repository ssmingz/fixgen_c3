class PlaceHold {
  public int GetIntPref(byte[] aPrefName, int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner24() ? 8 : 7), getAddress(), aPrefName, _retval);
  }
}
