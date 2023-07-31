class PlaceHold {
  public int GetNextSibling(int[] aNextSibling) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 11 : 10), getAddress(), aNextSibling);
  }
}
