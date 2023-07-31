class PlaceHold {
  public int pixelsHigh() {
    return OS.objc_msgSend(this.id, sel_pixelsHigh);
  }
}
