class PlaceHold {
  public NSString keyEquivalent() {
    int result = OS.objc_msgSend(this.id, sel_keyEquivalent);
    return result != 0 ? new NSString(result) : null;
  }
}
