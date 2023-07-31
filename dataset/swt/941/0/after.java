class PlaceHold {
  public long bitmapData() {
    return OS.objc_msgSend(this.id, sel_bitmapData);
  }
}
