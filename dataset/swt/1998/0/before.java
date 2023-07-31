class PlaceHold {
  public int GetPrincipalFromContext(int cx, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 23 : 24), getAddress(), cx, _retval);
  }
}
