class PlaceHold {
  public int GetTarget(int aTarget) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, getAddress(), aTarget);
  }
}
