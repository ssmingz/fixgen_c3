class PlaceHold {
  public int type() {
    return OS.objc_msgSend(this.id, sel_type);
  }
}
