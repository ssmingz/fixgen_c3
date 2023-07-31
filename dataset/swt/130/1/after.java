class PlaceHold {
  public int Reload(int reloadFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), reloadFlags);
  }
}
