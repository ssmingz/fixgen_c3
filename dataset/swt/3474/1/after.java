class PlaceHold {
  public int GetFocusedWindow(int[] aFocusedWindow) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aFocusedWindow);
  }
}
