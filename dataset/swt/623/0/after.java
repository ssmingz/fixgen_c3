class PlaceHold {
  public int GetOuterHeight(int[] aOuterHeight) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 61 : 58), getAddress(), aOuterHeight);
  }
}
