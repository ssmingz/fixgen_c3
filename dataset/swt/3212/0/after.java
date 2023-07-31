class PlaceHold {
  public int GetWindowByName(char[] aTargetName, int aCurrentWindow, int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 9, getAddress(), aTargetName, aCurrentWindow, _retval);
  }
}
