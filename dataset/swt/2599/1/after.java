class PlaceHold {
  public int bitsPerSample() {
    return ((int) (OS.objc_msgSend(this.id, sel_bitsPerSample)));
  }
}
