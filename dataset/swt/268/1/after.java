class PlaceHold {
  public int SetContainerWindow(long aContainerWindow) {
    return XPCOM.VtblCall(this.getSetterIndex("containerWindow"), getAddress(), aContainerWindow);
  }
}
