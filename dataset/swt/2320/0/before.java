class PlaceHold {
  public int GetSubjectPrincipal(long[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner24() ? 7 : IsXULRunner10() ? 10 : 11),
        getAddress(),
        _retval);
  }
}
