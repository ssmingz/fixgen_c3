class PlaceHold {
  public int Open(int aParent) {
    return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 1, getAddress(), aParent);
  }
}
