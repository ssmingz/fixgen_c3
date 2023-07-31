class PlaceHold {
  public int modifierFlags() {
    return OS.objc_msgSend(this.id, sel_modifierFlags);
  }
}
