class PlaceHold {
  public boolean hasAlpha() {
    return OS.objc_msgSend_bool(this.id, sel_hasAlpha);
  }
}
