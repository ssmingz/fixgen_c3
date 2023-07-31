class PlaceHold {
  public int SetChromeFlags(int chromeFlags) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), chromeFlags);
  }
}
