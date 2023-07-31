class PlaceHold {
  public int GetContent(long[] aContent) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 71 : 68), getAddress(), aContent);
  }
}
