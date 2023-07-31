class PlaceHold {
  public int GetTitle(long[] aTitle) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 22 : IsXULRunner10 ? 21 : 23),
        getAddress(),
        aTitle);
  }
}
