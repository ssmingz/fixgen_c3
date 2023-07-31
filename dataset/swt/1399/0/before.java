class PlaceHold {
  public NSSize paperSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_stret(result, this.id, sel_paperSize);
    return result;
  }
}
