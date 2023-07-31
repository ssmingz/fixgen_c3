class PlaceHold {
  public int SetFocusedWindow(int aFocusedWindow) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, getAddress(), aFocusedWindow);
  }
}
