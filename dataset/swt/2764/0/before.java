class PlaceHold {
  public int GetContainerWindow(int[] aContainerWindow) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), aContainerWindow);
  }
}
