class PlaceHold {
  public NSSize sizeValue() {
    NSSize result = new NSSize();
    OS.objc_msgSend_stret(result, this.id, sel_sizeValue);
    return result;
  }
}
