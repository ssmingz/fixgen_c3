class PlaceHold {
  public int GetChromeForWindow(int aWindow, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 8, getAddress(), aWindow, _retval);
  }
}
