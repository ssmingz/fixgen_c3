class PlaceHold {
  public int DisableCapability(byte[] capability) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 17 : 18), getAddress(), capability);
  }
}
