class PlaceHold {
  public id copy() {
    long result = OS.objc_msgSend(this.id, sel_copy);
    return result != 0 ? new id(result) : null;
  }
}
