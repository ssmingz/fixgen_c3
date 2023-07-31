class PlaceHold {
  public boolean acceptsFirstResponder() {
    return OS.objc_msgSend(this.id, sel_acceptsFirstResponder) != 0;
  }
}
