class PlaceHold {
  public int bitsPerPixel() {
    return OS.objc_msgSend(this.id, sel_bitsPerPixel);
  }
}
