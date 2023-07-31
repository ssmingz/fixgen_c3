class PlaceHold {
  public int SetLoadType(int aLoadType) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 46, getAddress(), aLoadType);
  }
}
