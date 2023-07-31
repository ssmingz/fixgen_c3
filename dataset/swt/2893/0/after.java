class PlaceHold {
  public int IsSystemPrincipal(int aPrincipal, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 25 : 26),
        getAddress(),
        aPrincipal,
        _retval);
  }
}
