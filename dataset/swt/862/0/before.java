class PlaceHold {
  public NSString stringByDeletingPathExtension() {
    int result = OS.objc_msgSend(this.id, sel_stringByDeletingPathExtension);
    return result == this.id ? this : result != 0 ? new NSString(result) : null;
  }
}
