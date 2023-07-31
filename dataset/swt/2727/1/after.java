class PlaceHold {
  public NSArray tableColumns() {
    long result = OS.objc_msgSend(this.id, sel_tableColumns);
    return result != 0 ? new NSArray(result) : null;
  }
}
