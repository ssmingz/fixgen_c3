class PlaceHold {
  public NSString familyName() {
    int result = OS.objc_msgSend(this.id, sel_familyName);
    return result != 0 ? new NSString(result) : null;
  }
}
