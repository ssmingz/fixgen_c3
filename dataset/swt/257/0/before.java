class PlaceHold {
  public int SetName(int aName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 7, getAddress(), aName);
  }
}
