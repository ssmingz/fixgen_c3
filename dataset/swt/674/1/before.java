class PlaceHold {
  public int Open(int aParent) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), aParent);
  }
}
