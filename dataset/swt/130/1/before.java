class PlaceHold {
  public int Reload(int reloadFlags) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 7, getAddress(), reloadFlags);
  }
}
