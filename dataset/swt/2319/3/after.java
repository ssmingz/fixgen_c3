class PlaceHold {
  public boolean isKeyWindow() {
    return OS.objc_msgSend_bool(this.id, sel_isKeyWindow);
  }
}
