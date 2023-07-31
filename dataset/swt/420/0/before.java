class PlaceHold {
  public int GetPassword(int aPassword) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 10, getAddress(), aPassword);
  }
}
