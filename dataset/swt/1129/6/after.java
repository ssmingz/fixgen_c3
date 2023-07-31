class PlaceHold {
  public int Activate() {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress());
  }
}
