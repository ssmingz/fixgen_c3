class PlaceHold {
  public boolean isVisible() {
    return OS.objc_msgSend(this.id, sel_isVisible) != 0;
  }
}
