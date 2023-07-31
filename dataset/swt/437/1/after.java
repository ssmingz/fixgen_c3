class PlaceHold {
  public int resizingMask() {
    return ((int) (OS.objc_msgSend(this.id, sel_resizingMask)));
  }
}
