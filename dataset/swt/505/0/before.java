class PlaceHold {
  public int UnregisterNotification(int aObserver) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), aObserver);
  }
}
