class PlaceHold {
  public int GetAsWStringWithSize(int[] size, int[] str) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 27 : 26), getAddress(), size, str);
  }
}
