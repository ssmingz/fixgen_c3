class PlaceHold {
  public NSDate dateValue() {
    int result = OS.objc_msgSend(this.id, sel_dateValue);
    return result != 0 ? new NSDate(result) : null;
  }
}
