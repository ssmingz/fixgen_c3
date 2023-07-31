class PlaceHold {
  public NSCell cell() {
    long result = OS.objc_msgSend(this.id, sel_cell);
    return result != 0 ? new NSCell(result) : null;
  }
}
