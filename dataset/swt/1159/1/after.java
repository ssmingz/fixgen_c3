class PlaceHold {
  public int GetLoadType(int[] aLoadType) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 45, getAddress(), aLoadType);
  }
}
