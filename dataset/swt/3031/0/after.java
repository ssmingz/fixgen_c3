class PlaceHold {
  public id initWithAttributes(int[] attribs) {
    int result = OS.objc_msgSend(this.id, sel_initWithAttributes_, attribs);
    return result != 0 ? new id(result) : null;
  }
}
