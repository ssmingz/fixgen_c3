class PlaceHold {
  public boolean hasAlpha() {
    return OS.objc_msgSend(this.id, sel_hasAlpha) != 0;
  }
}
