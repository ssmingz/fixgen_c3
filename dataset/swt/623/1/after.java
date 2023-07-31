class PlaceHold {
  public int GetWindowRoot(long[] aWindowRoot) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 64 : 61), getAddress(), aWindowRoot);
  }
}
