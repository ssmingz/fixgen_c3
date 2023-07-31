class PlaceHold {
  public int GetVisibility(boolean[] aVisibility) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aVisibility);
  }
}
