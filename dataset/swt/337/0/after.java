class PlaceHold {
  public NSString password() {
    long result = OS.objc_msgSend(this.id, sel_password);
    return result != 0 ? new NSString(result) : null;
  }
}
