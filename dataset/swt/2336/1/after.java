class PlaceHold {
  public NSDictionary linkTextAttributes() {
    long result = OS.objc_msgSend(this.id, sel_linkTextAttributes);
    return result != 0 ? new NSDictionary(result) : null;
  }
}
