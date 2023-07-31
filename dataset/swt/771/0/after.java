class PlaceHold {
  public int SetVisibility(boolean aVisibility) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, getAddress(), aVisibility);
  }
}
