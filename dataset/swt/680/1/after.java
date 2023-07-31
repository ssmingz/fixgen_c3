class PlaceHold {
  public int CheckSameOriginURI(long aSourceURI, long aTargetURI, int reportError) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner17 ? 21 : IsXULRunner10 ? 22 : 23),
        getAddress(),
        aSourceURI,
        aTargetURI,
        reportError);
  }
}
