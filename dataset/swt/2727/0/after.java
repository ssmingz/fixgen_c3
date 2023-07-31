class PlaceHold {
  public id mutableCopy() {
    long result = OS.objc_msgSend(this.id, sel_mutableCopy);
    return result != 0 ? new id(result) : null;
  }
}
