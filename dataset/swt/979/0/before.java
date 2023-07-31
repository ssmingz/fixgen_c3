class PlaceHold {
  public NSString resourcePath() {
    int result = OS.objc_msgSend(this.id, sel_resourcePath);
    return result != 0 ? new NSString(result) : null;
  }
}
