class PlaceHold {
  public NSDictionary markedTextAttributes() {
    long result = OS.objc_msgSend(this.id, sel_markedTextAttributes);
    return result != 0 ? new NSDictionary(result) : null;
  }
}
