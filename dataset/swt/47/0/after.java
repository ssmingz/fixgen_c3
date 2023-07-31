class PlaceHold {
  public int OnSecurityChange(int aWebProgress, int aRequest, int state) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 5, getAddress(), aWebProgress, aRequest, state);
  }
}
