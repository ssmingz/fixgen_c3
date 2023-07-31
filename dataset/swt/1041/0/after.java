class PlaceHold {
  public int GetPageYOffset(int[] aPageYOffset) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 49 : 48), getAddress(), aPageYOffset);
  }
}
