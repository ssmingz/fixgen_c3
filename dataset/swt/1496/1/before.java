class PlaceHold {
  public boolean worksWhenModal() {
    return OS.objc_msgSend(this.id, sel_worksWhenModal) != 0;
  }
}
