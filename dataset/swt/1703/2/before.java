class PlaceHold {
  public NSSize cellSize() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_cellSize);
    return result;
  }
}
