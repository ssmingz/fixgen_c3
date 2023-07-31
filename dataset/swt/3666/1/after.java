class PlaceHold {
  public int type() {
    return ((int) (OS.objc_msgSend(this.id, sel_type)));
  }
}
