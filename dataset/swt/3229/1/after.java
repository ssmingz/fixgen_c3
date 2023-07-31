class PlaceHold {
  public NSButtonCell cancelButtonCell() {
    long result = OS.objc_msgSend(this.id, sel_cancelButtonCell);
    return result != 0 ? new NSButtonCell(result) : null;
  }
}
