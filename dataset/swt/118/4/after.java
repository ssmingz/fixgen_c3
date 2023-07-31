class PlaceHold {
  public boolean ctrlKey() {
    return OS.objc_msgSend_bool(this.id, sel_ctrlKey);
  }
}
