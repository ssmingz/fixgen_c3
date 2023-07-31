class PlaceHold {
  public boolean isSessionOnly() {
    return OS.objc_msgSend(this.id, sel_isSessionOnly) != 0;
  }
}
