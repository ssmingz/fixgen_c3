class PlaceHold {
  public NSString resourcePath() {
    long result = OS.objc_msgSend(this.id, sel_resourcePath);
    return result != 0 ? new NSString(result) : null;
  }
}
