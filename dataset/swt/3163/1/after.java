class PlaceHold {
  public int GetComplexValue(byte[] aPrefName, nsID aType, long[] aValue) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner24 ? 10 : 9),
        getAddress(),
        aPrefName,
        aType,
        aValue);
  }
}
