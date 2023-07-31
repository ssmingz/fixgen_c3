class PlaceHold {
  public int GetTitle(long[] aTitle) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 21 : 23), getAddress(), aTitle);
  }
}
