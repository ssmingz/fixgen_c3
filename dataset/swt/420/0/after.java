class PlaceHold {
  public int GetPassword(int aPassword) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, getAddress(), aPassword);
  }
}
