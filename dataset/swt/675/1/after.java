class PlaceHold {
  public int windowNumber() {
    return ((int) (OS.objc_msgSend(this.id, sel_windowNumber)));
  }
}
