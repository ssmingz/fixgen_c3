class PlaceHold {
  public int samplesPerPixel() {
    return OS.objc_msgSend(this.id, sel_samplesPerPixel);
  }
}
