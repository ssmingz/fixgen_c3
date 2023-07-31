class PlaceHold {
  public NSString keyEquivalent() {
    long result = OS.objc_msgSend(this.id, sel_keyEquivalent);
    return result != 0 ? new NSString(result) : null;
  }
}
