class PlaceHold {
  public int SetAppType(int aAppType) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 30, getAddress(), aAppType);
  }
}
