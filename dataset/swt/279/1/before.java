class PlaceHold {
  public int GetName(int aName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 4 : 6), getAddress(), aName);
  }
}
