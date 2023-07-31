class PlaceHold {
  public int GetObjectPrincipal(int cx, int obj, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 19 : 20), getAddress(), cx, obj, _retval);
  }
}
