class PlaceHold {
  public int CheckSameOrigin(long aJSContext, long aTargetURI) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner17 ? 20 : IsXULRunner10 ? 21 : 22),
        getAddress(),
        aJSContext,
        aTargetURI);
  }
}
