class PlaceHold {
  public int GetName(int aName) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 4 : 6), getAddress(), aName);
  }
}
