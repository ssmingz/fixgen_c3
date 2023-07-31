class PlaceHold {
  public int SetScheme(int aScheme) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aScheme);
  }
}
