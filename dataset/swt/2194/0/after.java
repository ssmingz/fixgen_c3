class PlaceHold {
  public NSString bundleIdentifier() {
    long result = OS.objc_msgSend(this.id, sel_bundleIdentifier);
    return result != 0 ? new NSString(result) : null;
  }
}
