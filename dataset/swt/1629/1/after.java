class PlaceHold {
  public int bitsPerPixel() {
    return ((int) (OS.objc_msgSend(this.id, sel_bitsPerPixel)));
  }
}
