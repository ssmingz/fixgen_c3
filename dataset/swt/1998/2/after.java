class PlaceHold {
  public int GetCodebasePrincipal(int aURI, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 12 : 13),
        getAddress(),
        aURI,
        _retval);
  }
}
