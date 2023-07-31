class PlaceHold {
  public boolean ctrlKey() {
    return OS.objc_msgSend(this.id, sel_ctrlKey) != 0;
  }
}
