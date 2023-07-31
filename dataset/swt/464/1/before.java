class PlaceHold {
  public int GetSubjectPrincipal(int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 9 : 10), getAddress(), _retval);
  }
}
