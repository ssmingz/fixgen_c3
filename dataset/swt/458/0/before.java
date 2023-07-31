class PlaceHold {
  public int windowNumber() {
    return OS.objc_msgSend(this.id, sel_windowNumber);
  }
}
