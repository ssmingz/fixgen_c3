class PlaceHold {
  public int GetChannelPrincipal(int aChannel, int[] _retval) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 24 : 25),
        getAddress(),
        aChannel,
        _retval);
  }
}
