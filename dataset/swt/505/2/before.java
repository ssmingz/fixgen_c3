class PlaceHold {
  public int RegisterNotification(int aObserver) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), aObserver);
  }
}
