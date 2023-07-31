class PlaceHold {
  public int AutoRegister(int aSpec) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), aSpec);
  }
}
