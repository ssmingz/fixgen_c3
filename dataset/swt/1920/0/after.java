class PlaceHold {
  public int SetOnunload(long cx, long aOnunload) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 125 : 121), getAddress(), cx, aOnunload);
  }
}
