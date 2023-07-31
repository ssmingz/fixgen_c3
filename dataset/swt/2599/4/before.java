class PlaceHold {
  public int state() {
    return OS.objc_msgSend(this.id, sel_state);
  }
}
