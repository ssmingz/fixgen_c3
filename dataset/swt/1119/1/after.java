class PlaceHold {
  public int SetSecurityPolicy(int aSecurityPolicy) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 7 : 6), getAddress(), aSecurityPolicy);
  }
}
