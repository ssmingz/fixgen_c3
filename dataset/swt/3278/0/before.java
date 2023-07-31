class PlaceHold {
  public boolean documentViewShouldHandlePrint() {
    return OS.objc_msgSend(this.id, sel_documentViewShouldHandlePrint) != 0;
  }
}
