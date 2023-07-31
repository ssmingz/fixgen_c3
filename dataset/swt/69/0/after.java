class PlaceHold {
  public int GetScheme(int aScheme) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aScheme);
  }
}
