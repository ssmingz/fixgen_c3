class PlaceHold {
  public id sender() {
    int result = OS.objc_msgSend(this.id, sel_sender);
    return result != 0 ? new id(result) : null;
  }
}
