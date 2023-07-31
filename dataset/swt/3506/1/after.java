class PlaceHold {
  public int SetChromeFlags(int aChromeFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aChromeFlags);
  }
}
