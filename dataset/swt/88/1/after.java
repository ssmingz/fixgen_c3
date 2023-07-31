class PlaceHold {
  public int GetOffline(boolean[] aOffline) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aOffline);
  }
}
