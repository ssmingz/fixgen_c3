class PlaceHold {
  public NSString localizedDescription() {
    long result = OS.objc_msgSend(this.id, sel_localizedDescription);
    return result != 0 ? new NSString(result) : null;
  }
}
