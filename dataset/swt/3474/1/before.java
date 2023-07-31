class PlaceHold {
  public int GetFocusedWindow(int[] aFocusedWindow) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), aFocusedWindow);
  }
}
