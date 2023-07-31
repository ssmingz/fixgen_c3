class PlaceHold {
  public int GetTitle(int[] aTitle) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), aTitle);
  }
}
