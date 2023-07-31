class PlaceHold {
  public int SetOffline(boolean aOffline) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 8, getAddress(), aOffline);
  }
}
