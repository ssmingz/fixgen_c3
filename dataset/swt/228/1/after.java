class PlaceHold {
  public int GetTop(int[] aTop) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, getAddress(), aTop);
  }
}
