class PlaceHold {
  public int SetFocusedWindow(int aFocusedWindow) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), aFocusedWindow);
  }
}
