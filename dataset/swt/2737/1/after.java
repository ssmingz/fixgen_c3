class PlaceHold {
  public int SetPath(int aPath) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, getAddress(), aPath);
  }
}
