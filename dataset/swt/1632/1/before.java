class PlaceHold {
  public int GetSystemPrincipal(long[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 10 : 11), getAddress(), _retval);
  }
}
