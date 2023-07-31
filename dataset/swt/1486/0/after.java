class PlaceHold {
  public boolean resignFirstResponder() {
    return OS.objc_msgSend_bool(this.id, sel_resignFirstResponder);
  }
}
