class PlaceHold {
  public int depth() {
    return OS.objc_msgSend(this.id, sel_depth);
  }
}
