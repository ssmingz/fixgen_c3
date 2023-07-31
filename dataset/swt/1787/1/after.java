class PlaceHold {
  public NSString titleOfSelectedItem() {
    long result = OS.objc_msgSend(this.id, sel_titleOfSelectedItem);
    return result != 0 ? new NSString(result) : null;
  }
}
