class PlaceHold {
  public NSSize size() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_size);
    return result;
  }
}
