class PlaceHold {
  public boolean isVisible() {
    return OS.objc_msgSend_bool(this.id, sel_isVisible);
  }
}
