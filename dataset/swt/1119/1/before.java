class PlaceHold {
  public int SetSecurityPolicy(int aSecurityPolicy) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 7, getAddress(), aSecurityPolicy);
  }
}
