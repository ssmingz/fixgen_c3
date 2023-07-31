class PlaceHold {
  public boolean documentViewShouldHandlePrint() {
    return OS.objc_msgSend_bool(this.id, sel_documentViewShouldHandlePrint);
  }
}
