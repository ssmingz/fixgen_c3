class PlaceHold {
  public int GetSubjectPrincipal(long[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 9 : 10), getAddress(), _retval);
  }
}
