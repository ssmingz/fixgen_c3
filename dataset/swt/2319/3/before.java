class PlaceHold {
  public boolean isKeyWindow() {
    return OS.objc_msgSend(this.id, sel_isKeyWindow) != 0;
  }
}
