class PlaceHold {
  public int GetSecurityPolicy(int[] aSecurityPolicy) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 6, getAddress(), aSecurityPolicy);
  }
}
