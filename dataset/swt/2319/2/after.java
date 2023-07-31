class PlaceHold {
  public boolean isRunning() {
    return OS.objc_msgSend_bool(this.id, sel_isRunning);
  }
}
