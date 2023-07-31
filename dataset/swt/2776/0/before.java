class PlaceHold {
  public NSString path() {
    int result = OS.objc_msgSend(this.id, sel_path);
    return result != 0 ? new NSString(result) : null;
  }
}
