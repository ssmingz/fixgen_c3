class PlaceHold {
  public id object() {
    int result = OS.objc_msgSend(this.id, sel_object);
    return result != 0 ? new id(result) : null;
  }
}
