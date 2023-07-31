class PlaceHold {
  public NSArray availableFonts() {
    long result = OS.objc_msgSend(this.id, sel_availableFonts);
    return result != 0 ? new NSArray(result) : null;
  }
}
