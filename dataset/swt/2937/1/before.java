class PlaceHold {
  public boolean isOpaque() {
    return OS.objc_msgSend(this.id, sel_isOpaque) != 0;
  }
}
