class PlaceHold {
  public int GetPath(int aPath) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 29, getAddress(), aPath);
  }
}
