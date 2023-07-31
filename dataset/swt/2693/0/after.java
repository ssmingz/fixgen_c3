class PlaceHold {
  public int GetAsStringWithSize(int[] size, int[] str) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 26 : 25), getAddress(), size, str);
  }
}
