class PlaceHold {
  public int SetObserver(int[] aObserver) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, getAddress(), aObserver);
  }
}
