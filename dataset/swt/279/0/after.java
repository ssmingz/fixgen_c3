class PlaceHold {
  public int GetParent(int[] aParent) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 24 : 2), getAddress(), aParent);
  }
}
