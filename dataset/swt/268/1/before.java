class PlaceHold {
  public int SetContainerWindow(long aContainerWindow) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aContainerWindow);
  }
}
