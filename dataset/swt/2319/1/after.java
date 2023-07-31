class PlaceHold {
  public boolean runOperation() {
    return OS.objc_msgSend_bool(this.id, sel_runOperation);
  }
}
