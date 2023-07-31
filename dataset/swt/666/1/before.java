class PlaceHold {
  public boolean isPlanar() {
    return OS.objc_msgSend(this.id, sel_isPlanar) != 0;
  }
}
