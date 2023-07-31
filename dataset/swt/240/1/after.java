class PlaceHold {
  public int SetAppType(int aAppType) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 30, getAddress(), aAppType);
  }
}
