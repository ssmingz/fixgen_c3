class PlaceHold {
  public NSString user() {
    long result = OS.objc_msgSend(this.id, sel_user);
    return result != 0 ? new NSString(result) : null;
  }
}
