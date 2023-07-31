class PlaceHold {
  public boolean becomeFirstResponder() {
    return OS.objc_msgSend_bool(this.id, sel_becomeFirstResponder);
  }
}
