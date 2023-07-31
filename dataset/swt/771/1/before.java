class PlaceHold {
  public int GetVisibility(boolean[] aVisibility) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), aVisibility);
  }
}
