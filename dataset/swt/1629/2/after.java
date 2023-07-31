class PlaceHold {
  public int samplesPerPixel() {
    return ((int) (OS.objc_msgSend(this.id, sel_samplesPerPixel)));
  }
}
