class PlaceHold {
  public boolean isEqualTo(id object) {
    return OS.objc_msgSend_bool(this.id, sel_isEqualTo_, object != null ? object.id : 0);
  }
}
