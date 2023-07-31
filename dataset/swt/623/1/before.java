class PlaceHold {
  public int GetWindowRoot(long[] aWindowRoot) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 61, getAddress(), aWindowRoot);
  }
}
