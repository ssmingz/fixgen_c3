class PlaceHold {
  public boolean wantsToHandleMouseEvents() {
    return OS.objc_msgSend_bool(this.id, sel_wantsToHandleMouseEvents);
  }
}
