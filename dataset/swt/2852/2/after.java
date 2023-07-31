class PlaceHold {
  public int depth() {
    return ((int) (OS.objc_msgSend(this.id, sel_depth)));
  }
}
