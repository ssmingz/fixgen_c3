class PlaceHold {
  public int bytesPerRow() {
    return ((int) (OS.objc_msgSend(this.id, sel_bytesPerRow)));
  }
}
