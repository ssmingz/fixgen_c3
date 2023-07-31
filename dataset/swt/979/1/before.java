class PlaceHold {
  public NSString bundlePath() {
    int result = OS.objc_msgSend(this.id, sel_bundlePath);
    return result != 0 ? new NSString(result) : null;
  }
}
