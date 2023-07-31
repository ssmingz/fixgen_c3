class PlaceHold {
  public int SetContainerWindow(int aContainerWindow) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), aContainerWindow);
  }
}
