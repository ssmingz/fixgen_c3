class PlaceHold {
  public int IsCapabilityEnabled(byte[] capability, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 14 : 15),
        getAddress(),
        capability,
        _retval);
  }
}
