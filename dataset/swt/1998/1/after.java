class PlaceHold {
  public int GetObjectPrincipal(int cx, int obj, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 19 : 20),
        getAddress(),
        cx,
        obj,
        _retval);
  }
}
