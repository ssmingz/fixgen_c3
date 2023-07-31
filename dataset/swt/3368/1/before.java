class PlaceHold {
  public int SetAllowPlugins(boolean allowPlugins) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 19, getAddress(), allowPlugins);
  }
}
