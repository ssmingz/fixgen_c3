class PlaceHold {
  public int GetSystemPrincipal(int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 10 : 11), getAddress(), _retval);
  }
}
