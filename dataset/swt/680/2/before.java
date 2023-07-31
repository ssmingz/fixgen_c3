class PlaceHold {
  public int CheckSameOrigin(long aJSContext, long aTargetURI) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 21 : 22),
        getAddress(),
        aJSContext,
        aTargetURI);
  }
}
