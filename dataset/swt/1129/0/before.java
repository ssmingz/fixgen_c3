class PlaceHold {
  public int Destroy() {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress());
  }
}
