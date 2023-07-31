class PlaceHold {
  public boolean isPlanar() {
    return OS.objc_msgSend_bool(this.id, sel_isPlanar);
  }
}
