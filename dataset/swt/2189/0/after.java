class PlaceHold {
  public id sender() {
    long result = OS.objc_msgSend(this.id, sel_sender);
    return result != 0 ? new id(result) : null;
  }
}
