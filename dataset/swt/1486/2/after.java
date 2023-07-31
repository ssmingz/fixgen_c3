class PlaceHold {
  public boolean acceptsFirstResponder() {
    return OS.objc_msgSend_bool(this.id, sel_acceptsFirstResponder);
  }
}
