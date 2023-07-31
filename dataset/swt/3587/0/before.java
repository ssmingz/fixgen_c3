class PlaceHold {
  public int SetObserver(int aObserver) {
    return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 5, getAddress(), aObserver);
  }
}
