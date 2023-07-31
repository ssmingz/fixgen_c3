class PlaceHold {
  public int SetChromeFlags(int chromeFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), chromeFlags);
  }
}
