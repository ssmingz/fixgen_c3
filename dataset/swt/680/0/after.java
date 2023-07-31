class PlaceHold {
  public int SubjectPrincipalIsSystem(int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner17 ? 19 : IsXULRunner10 ? 20 : 21),
        getAddress(),
        _retval);
  }
}
