class PlaceHold {
  public int count() {
    return ((int) (OS.objc_msgSend(this.id, sel_count)));
  }
}
