class PlaceHold {
  public int Btoa(long aBase64Data, long _retval) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 38 : 35), getAddress(), aBase64Data, _retval);
  }
}
