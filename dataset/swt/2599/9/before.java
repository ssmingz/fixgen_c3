class PlaceHold {
  public int count() {
    return OS.objc_msgSend(this.id, sel_count);
  }
}
