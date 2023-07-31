class PlaceHold {
  public int SetTitle(char[] aTitle) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aTitle);
  }
}
