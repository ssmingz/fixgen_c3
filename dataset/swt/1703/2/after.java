class PlaceHold {
  public NSSize cellSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_stret(result, this.id, sel_cellSize);
    return result;
  }
}
