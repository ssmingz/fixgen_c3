class PlaceHold {
  public boolean isOpaque() {
    return OS.objc_msgSend_bool(this.id, sel_isOpaque);
  }
}
