class PlaceHold {
  public NSString filename() {
    long result = OS.objc_msgSend(this.id, sel_filename);
    return result != 0 ? new NSString(result) : null;
  }
}
