class PlaceHold {
  public int SetIntPref(byte[] aPrefName, int aValue) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner24() ? 9 : 8), getAddress(), aPrefName, aValue);
  }
}
