class PlaceHold {
  public int CheckLoadURIFromScript(int cx, int uri) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (Is8 ? 2 : 3), getAddress(), cx, uri);
  }
}
