class PlaceHold {
  public int resizingMask() {
    return OS.objc_msgSend(this.id, sel_resizingMask);
  }
}
