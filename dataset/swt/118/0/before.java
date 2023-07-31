class PlaceHold {
  public boolean altKey() {
    return OS.objc_msgSend(this.id, sel_altKey) != 0;
  }
}
