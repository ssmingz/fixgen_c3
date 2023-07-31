class PlaceHold {
  public NSArray filenames() {
    long result = OS.objc_msgSend(this.id, sel_filenames);
    return result != 0 ? new NSArray(result) : null;
  }
}
