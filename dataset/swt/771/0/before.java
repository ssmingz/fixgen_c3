class PlaceHold {
  public int SetVisibility(boolean aVisibility) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 16, getAddress(), aVisibility);
  }
}
