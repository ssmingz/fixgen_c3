class PlaceHold {
  public boolean isFlipped() {
    return OS.objc_msgSend_bool(this.id, sel_isFlipped);
  }
}
