class PlaceHold {
  public int SetName(int aName) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 5 : 7), getAddress(), aName);
  }
}
