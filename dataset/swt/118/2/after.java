class PlaceHold {
  public boolean isSessionOnly() {
    return OS.objc_msgSend_bool(this.id, sel_isSessionOnly);
  }
}
