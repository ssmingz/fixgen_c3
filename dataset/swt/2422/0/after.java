class PlaceHold {
  public NSString stringByAppendingString(NSString aString) {
    long result =
        OS.objc_msgSend(this.id, sel_stringByAppendingString_, aString != null ? aString.id : 0);
    return result == this.id ? this : result != 0 ? new NSString(result) : null;
  }
}
