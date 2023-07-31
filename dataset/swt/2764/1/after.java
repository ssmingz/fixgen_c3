class PlaceHold {
  public int SetContainerWindow(int aContainerWindow) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aContainerWindow);
  }
}
