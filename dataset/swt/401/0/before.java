class PlaceHold {
  public NSArray filenames() {
    int result = OS.objc_msgSend(this.id, sel_filenames);
    return result != 0 ? new NSArray(result) : null;
  }
}
