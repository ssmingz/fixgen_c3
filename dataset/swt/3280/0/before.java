class PlaceHold {
  public boolean isEqualTo(id object) {
    return OS.objc_msgSend(this.id, sel_isEqualTo_, object != null ? object.id : 0) != 0;
  }
}
