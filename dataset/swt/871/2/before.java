class PlaceHold {
  public NSSize containerSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_containerSize);
    return result;
  }
}
