class PlaceHold {
  public int SetComplexValue(byte[] aPrefName, nsID aType, long aValue) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner24 ? 11 : 10),
        getAddress(),
        aPrefName,
        aType,
        aValue);
  }
}
