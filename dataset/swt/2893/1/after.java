class PlaceHold {
  public int CanExecuteScripts(int cx, int principal, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 8 : 9),
        getAddress(),
        cx,
        principal,
        _retval);
  }
}
