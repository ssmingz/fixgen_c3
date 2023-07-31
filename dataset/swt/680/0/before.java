class PlaceHold {
  public int SubjectPrincipalIsSystem(int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 20 : 21), getAddress(), _retval);
  }
}
