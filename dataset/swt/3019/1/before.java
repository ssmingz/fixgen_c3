class PlaceHold {
  public NSImage initWithContentsOfFile(NSString fileName) {
    int result =
        OS.objc_msgSend(this.id, sel_initWithContentsOfFile_, fileName != null ? fileName.id : 0);
    return result == this.id ? this : result != 0 ? new NSImage(result) : null;
  }
}
