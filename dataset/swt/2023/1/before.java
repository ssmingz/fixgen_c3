class PlaceHold {
  public NSURLProtectionSpace protectionSpace() {
    int result = OS.objc_msgSend(this.id, sel_protectionSpace);
    return result != 0 ? new NSURLProtectionSpace(result) : null;
  }
}
