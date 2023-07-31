class PlaceHold {
  public int GetObserver(int[] aObserver) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, getAddress(), aObserver);
  }
}
