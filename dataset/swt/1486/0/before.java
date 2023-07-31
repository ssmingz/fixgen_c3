class PlaceHold {
  public boolean resignFirstResponder() {
    return OS.objc_msgSend(this.id, sel_resignFirstResponder) != 0;
  }
}
