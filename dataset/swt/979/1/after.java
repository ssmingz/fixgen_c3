class PlaceHold {
  public NSString bundlePath() {
    long result = OS.objc_msgSend(this.id, sel_bundlePath);
    return result != 0 ? new NSString(result) : null;
  }
}
