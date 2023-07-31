class PlaceHold {
  public int SetName(int aName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 5 : 7), getAddress(), aName);
  }
}
