class PlaceHold {
  public int Create() {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress());
  }
}
