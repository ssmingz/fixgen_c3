class PlaceHold {
  public int runModal() {
    return OS.objc_msgSend(this.id, sel_runModal);
  }
}
