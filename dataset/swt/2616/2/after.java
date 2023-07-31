class PlaceHold {
  public int GetChromeFlags(int[] chromeFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), chromeFlags);
  }
}
