class PlaceHold {
  public int state() {
    return ((int) (OS.objc_msgSend(this.id, sel_state)));
  }
}
