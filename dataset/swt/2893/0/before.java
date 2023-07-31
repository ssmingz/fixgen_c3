class PlaceHold {
  public int IsSystemPrincipal(int aPrincipal, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 25 : 26), getAddress(), aPrincipal, _retval);
  }
}
