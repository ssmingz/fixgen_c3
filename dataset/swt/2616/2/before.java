class PlaceHold {
  public int GetChromeFlags(int[] chromeFlags) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), chromeFlags);
  }
}
