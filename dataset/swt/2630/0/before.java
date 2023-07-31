class PlaceHold {
  public int SetParentContentListener(int aParentContentListener) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 8, getAddress(), aParentContentListener);
  }
}
