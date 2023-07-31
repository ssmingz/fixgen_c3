class PlaceHold {
  public int modifierFlags() {
    return ((int) (OS.objc_msgSend(this.id, sel_modifierFlags)));
  }
}
