class PlaceHold {
  public int GetOuterHeight(int[] aOuterHeight) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 58, getAddress(), aOuterHeight);
  }
}
