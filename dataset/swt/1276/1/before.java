class PlaceHold {
  public int GetParent(int[] aParent) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 24 : 2), getAddress(), aParent);
  }
}
