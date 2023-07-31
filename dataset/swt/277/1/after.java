class PlaceHold {
  public int CheckLoadURIFromScript(int cx, int uri) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner10 ? 2 : 3), getAddress(), cx, uri);
  }
}
