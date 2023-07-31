class PlaceHold {
  public int SetPort(int aPort) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 17, getAddress(), aPort);
  }
}
