class PlaceHold {
  public int GetSecurityPolicy(int[] aSecurityPolicy) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 6 : 5), getAddress(), aSecurityPolicy);
  }
}
