class PlaceHold {
  public NSString jobDisposition() {
    int result = OS.objc_msgSend(this.id, sel_jobDisposition);
    return result != 0 ? new NSString(result) : null;
  }
}
