class PlaceHold {
  public int GetBoolPref(byte[] aPrefName, boolean[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 9, getAddress(), aPrefName, retVal);
  }
}
