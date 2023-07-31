class PlaceHold {
  public int CanExecuteScripts(long cx, long principal, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 8 : 9),
        getAddress(),
        cx,
        principal,
        _retval);
  }
}
