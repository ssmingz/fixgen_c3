class PlaceHold {
  public int SetTitle(char[] aTitle) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 22 : 24), getAddress(), aTitle);
  }
}
