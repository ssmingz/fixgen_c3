class PlaceHold {
  public int OnHideTooltip() {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, getAddress());
  }
}
