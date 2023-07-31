class PlaceHold {
  public int bitsPerSample() {
    return OS.objc_msgSend(this.id, sel_bitsPerSample);
  }
}
