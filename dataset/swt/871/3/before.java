class PlaceHold {
  public NSSize minimumSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_minimumSize);
    return result;
  }
}
