class PlaceHold {
  public int GetAppType(int[] aAppType) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 29, getAddress(), aAppType);
  }
}
