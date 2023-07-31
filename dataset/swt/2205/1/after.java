class PlaceHold {
  public int EnableCapability(byte[] capability) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner17 ? 17 : IsXULRunner10 ? 15 : 16),
        getAddress(),
        capability);
  }
}
