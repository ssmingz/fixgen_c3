class PlaceHold {
  public int GetPort(int[] aPort) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, getAddress(), aPort);
  }
}
