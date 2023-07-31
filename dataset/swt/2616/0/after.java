class PlaceHold {
  public int Stop(int stopFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, getAddress(), stopFlags);
  }
}
