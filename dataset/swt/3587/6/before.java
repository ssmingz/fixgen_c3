class PlaceHold {
  public int GetObserver(int[] aObserver) {
    return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 4, getAddress(), aObserver);
  }
}
