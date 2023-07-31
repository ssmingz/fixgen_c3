class PlaceHold {
  public int RevertCapability(byte[] capability) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 16 : 17), getAddress(), capability);
  }
}
