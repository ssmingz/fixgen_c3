class PlaceHold {
  public int bytesPerRow() {
    return OS.objc_msgSend(this.id, sel_bytesPerRow);
  }
}
