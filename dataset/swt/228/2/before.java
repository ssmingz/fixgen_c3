class PlaceHold {
  public int GetSpec(int aSpec) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), aSpec);
  }
}
