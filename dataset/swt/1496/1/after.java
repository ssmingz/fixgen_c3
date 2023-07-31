class PlaceHold {
  public boolean worksWhenModal() {
    return OS.objc_msgSend_bool(this.id, sel_worksWhenModal);
  }
}
