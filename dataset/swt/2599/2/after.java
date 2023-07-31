class PlaceHold {
  public int runModal() {
    return ((int) (OS.objc_msgSend(this.id, sel_runModal)));
  }
}
