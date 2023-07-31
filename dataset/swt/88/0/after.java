class PlaceHold {
  public int SetOffline(boolean aOffline) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, getAddress(), aOffline);
  }
}
