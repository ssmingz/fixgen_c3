class PlaceHold {
  public NSArray availableFontFamilies() {
    long result = OS.objc_msgSend(this.id, sel_availableFontFamilies);
    return result != 0 ? new NSArray(result) : null;
  }
}
