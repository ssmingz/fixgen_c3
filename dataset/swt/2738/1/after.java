class PlaceHold {
  public int GetPreviousSibling(int[] aPreviousSibling) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 10 : 9), getAddress(), aPreviousSibling);
  }
}
