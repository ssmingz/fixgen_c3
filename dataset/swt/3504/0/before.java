class PlaceHold {
  public int pixelsWide() {
    return OS.objc_msgSend(this.id, sel_pixelsWide);
  }
}
