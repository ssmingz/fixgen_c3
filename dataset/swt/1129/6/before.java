class PlaceHold {
  public int Activate() {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress());
  }
}
