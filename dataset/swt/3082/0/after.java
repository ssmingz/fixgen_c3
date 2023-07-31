class PlaceHold {
  public NSString displayName() {
    long result = OS.objc_msgSend(this.id, sel_displayName);
    return result != 0 ? new NSString(result) : null;
  }
}
