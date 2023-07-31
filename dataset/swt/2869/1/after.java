class PlaceHold {
  public int CheckFunctionAccess(long cx, long funObj, long targetObj) {
    return XPCOM.VtblCall(
        nsIXPCSecurityManager.LAST_METHOD_ID + (IsXULRunner17 ? 5 : IsXULRunner10 ? 7 : 8),
        getAddress(),
        cx,
        funObj,
        targetObj);
  }
}
