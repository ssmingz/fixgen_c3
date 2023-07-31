class PlaceHold {
  public int SizeBrowserTo(int aCX, int aCY) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aCX, aCY);
  }
}
