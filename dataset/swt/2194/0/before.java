class PlaceHold {
  public NSString bundleIdentifier() {
    int result = OS.objc_msgSend(this.id, sel_bundleIdentifier);
    return result != 0 ? new NSString(result) : null;
  }
}
