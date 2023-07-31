class PlaceHold {
  public int GetPath(int aPath) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, getAddress(), aPath);
  }
}
