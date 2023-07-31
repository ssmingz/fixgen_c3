class PlaceHold {
  public long windowNumber() {
    return OS.objc_msgSend(this.id, sel_windowNumber);
  }
}
