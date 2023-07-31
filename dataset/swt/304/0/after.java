class PlaceHold {
  public int SetPort(int aPort) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, getAddress(), aPort);
  }
}
