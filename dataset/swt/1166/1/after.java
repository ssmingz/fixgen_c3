class PlaceHold {
  public int GetSecurityPolicy(int[] aSecurityPolicy) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 6 : 5), getAddress(), aSecurityPolicy);
  }
}
