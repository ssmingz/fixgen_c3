class PlaceHold {
  public int GetType(int aType) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aType);
  }
}
