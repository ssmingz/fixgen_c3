class PlaceHold {
  public int GetAsStringWithSize(int[] size, int[] str) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, getAddress(), size, str);
  }
}
