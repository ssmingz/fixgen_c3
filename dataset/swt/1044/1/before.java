class PlaceHold {
  public int EnableCapability(byte[] capability) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 15 : 16), getAddress(), capability);
  }
}
