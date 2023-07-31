class PlaceHold {
  public int SetPassword(int aPassword) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 11, getAddress(), aPassword);
  }
}
