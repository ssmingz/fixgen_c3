class PlaceHold {
  public boolean isEqual(id object) {
    return OS.objc_msgSend(this.id, sel_isEqual_, object != null ? object.id : 0) != 0;
  }
}
