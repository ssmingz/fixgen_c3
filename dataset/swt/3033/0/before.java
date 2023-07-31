class PlaceHold {
  public int SetDomain(int aDomain) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + (Is8 ? 16 : 15), getAddress(), aDomain);
  }
}
