class PlaceHold {
  public int clickCount() {
    return OS.objc_msgSend(this.id, sel_clickCount);
  }
}
