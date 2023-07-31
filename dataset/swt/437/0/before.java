class PlaceHold {
  public int styleMask() {
    return OS.objc_msgSend(this.id, sel_styleMask);
  }
}
