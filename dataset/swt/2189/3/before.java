class PlaceHold {
  public NSString user() {
    int result = OS.objc_msgSend(this.id, sel_user);
    return result != 0 ? new NSString(result) : null;
  }
}
