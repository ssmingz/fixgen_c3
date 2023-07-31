class PlaceHold {
  public NSSize contentSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_contentSize);
    return result;
  }
}
