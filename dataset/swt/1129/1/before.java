class PlaceHold {
  public int Create() {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress());
  }
}
