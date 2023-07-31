class PlaceHold {
  public int bytes() {
    return ((int) (OS.objc_msgSend(this.id, sel_bytes)));
  }
}
