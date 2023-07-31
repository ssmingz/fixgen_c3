class PlaceHold {
  public int CheckLoadURIStrWithPrincipal(long aPrincipal, long uri, int flags) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 5 : 6),
        getAddress(),
        aPrincipal,
        uri,
        flags);
  }
}
