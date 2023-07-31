class PlaceHold {
  public int SetTitle(char[] aTitle) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 7, getAddress(), aTitle);
  }
}
