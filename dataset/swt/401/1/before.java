class PlaceHold {
  public NSString filename() {
    int result = OS.objc_msgSend(this.id, sel_filename);
    return result != 0 ? new NSString(result) : null;
  }
}
