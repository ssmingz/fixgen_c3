class PlaceHold {
  public int GetPort(int[] aPort) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 16, getAddress(), aPort);
  }
}
