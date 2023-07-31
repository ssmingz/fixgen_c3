class PlaceHold {
  public int GetAllowPlugins(boolean[] allowPlugins) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, getAddress(), allowPlugins);
  }
}
