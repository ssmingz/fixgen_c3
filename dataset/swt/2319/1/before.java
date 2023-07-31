class PlaceHold {
  public boolean runOperation() {
    return OS.objc_msgSend(this.id, sel_runOperation) != 0;
  }
}
