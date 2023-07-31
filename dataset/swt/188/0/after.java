class PlaceHold {
  public int GetTitle(int[] aTitle) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, getAddress(), aTitle);
  }
}
