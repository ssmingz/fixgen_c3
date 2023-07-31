class PlaceHold {
  public NSString itemIdentifier() {
    long result = OS.objc_msgSend(this.id, sel_itemIdentifier);
    return result != 0 ? new NSString(result) : null;
  }
}
