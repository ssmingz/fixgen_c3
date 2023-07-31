class PlaceHold {
  public int GetChromeFlags(int[] aChromeFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aChromeFlags);
  }
}
