class PlaceHold {
  public int GetCrypto(long[] aCrypto) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 74 : 71), getAddress(), aCrypto);
  }
}
