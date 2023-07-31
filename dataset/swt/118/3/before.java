class PlaceHold {
  public boolean becomeFirstResponder() {
    return OS.objc_msgSend(this.id, sel_becomeFirstResponder) != 0;
  }
}
