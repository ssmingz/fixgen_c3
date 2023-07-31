class PlaceHold {
  public NSString stringByDeletingLastPathComponent() {
    long result = OS.objc_msgSend(this.id, sel_stringByDeletingLastPathComponent);
    return result == this.id ? this : result != 0 ? new NSString(result) : null;
  }
}
