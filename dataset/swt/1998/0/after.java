class PlaceHold {
  public int GetPrincipalFromContext(int cx, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 23 : 24),
        getAddress(),
        cx,
        _retval);
  }
}
