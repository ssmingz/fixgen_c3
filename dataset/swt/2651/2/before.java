class PlaceHold {
  public NSObject init() {
    int result = OS.objc_msgSend(this.id, sel_init);
    return result == this.id ? this : result != 0 ? new NSObject(result) : null;
  }
}
