class PlaceHold {
  public boolean isRunning() {
    return OS.objc_msgSend(this.id, sel_isRunning) != 0;
  }
}
