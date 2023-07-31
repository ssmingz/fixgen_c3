class PlaceHold {
  public NSButtonCell searchButtonCell() {
    long result = OS.objc_msgSend(this.id, sel_searchButtonCell);
    return result != 0 ? new NSButtonCell(result) : null;
  }
}
