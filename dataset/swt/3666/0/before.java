class PlaceHold {
  public int bytes() {
    return OS.objc_msgSend(this.id, sel_bytes);
  }
}
