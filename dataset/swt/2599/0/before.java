class PlaceHold {
  public int intValue() {
    return OS.objc_msgSend(this.id, sel_intValue);
  }
}
