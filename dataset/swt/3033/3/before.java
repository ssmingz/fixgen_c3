class PlaceHold {
  public int GetURI(int[] aURI) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + (Is8 ? 14 : 13), getAddress(), aURI);
  }
}
