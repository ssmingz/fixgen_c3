class PlaceHold {
  public int GetAsWStringWithSize(int[] size, int[] str) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, getAddress(), size, str);
  }
}
