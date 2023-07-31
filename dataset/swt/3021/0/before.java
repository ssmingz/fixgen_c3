class PlaceHold {
  public int retainCount() {
    return OS.objc_msgSend(this.id, sel_retainCount);
  }
}
