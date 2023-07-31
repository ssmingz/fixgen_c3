class PlaceHold {
  public NSIndexSet columnIndexesInRect(NSRect rect) {
    int result = OS.objc_msgSend(this.id, sel_columnIndexesInRect_, rect);
    return result != 0 ? new NSIndexSet(result) : null;
  }
}
