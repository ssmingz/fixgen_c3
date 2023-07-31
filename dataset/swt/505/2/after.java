class PlaceHold {
  public int RegisterNotification(int aObserver) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress(), aObserver);
  }
}
