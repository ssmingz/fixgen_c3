class PlaceHold {
  public int IsCapabilityEnabled(byte[] capability, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 14 : 15), getAddress(), capability, _retval);
  }
}
