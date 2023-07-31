class PlaceHold {
  public int OnHideTooltip() {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress());
  }
}
