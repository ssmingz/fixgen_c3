class PlaceHold {
  public NSString stringByAppendingPathExtension(NSString str) {
    int result =
        OS.objc_msgSend(this.id, sel_stringByAppendingPathExtension_, str != null ? str.id : 0);
    return result == this.id ? this : result != 0 ? new NSString(result) : null;
  }
}
