class PlaceHold {
  public int GetType(int aType) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), aType);
  }
}
