class PlaceHold {
  public int GetIntPref(byte[] aPrefName, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 13, getAddress(), aPrefName, retVal);
  }
}
