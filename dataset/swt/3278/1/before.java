class PlaceHold {
  public boolean wantsToHandleMouseEvents() {
    return OS.objc_msgSend(this.id, sel_wantsToHandleMouseEvents) != 0;
  }
}
