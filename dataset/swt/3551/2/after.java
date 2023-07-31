class PlaceHold {
  public NSString stringByAppendingPathComponent(NSString str) {
    long result =
        OS.objc_msgSend(this.id, sel_stringByAppendingPathComponent_, str != null ? str.id : 0);
    return result == this.id ? this : result != 0 ? new NSString(result) : null;
  }
}
