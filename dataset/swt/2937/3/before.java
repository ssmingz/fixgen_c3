class PlaceHold {
  public boolean isFlipped() {
    return OS.objc_msgSend(this.id, sel_isFlipped) != 0;
  }
}
