class PlaceHold {
  public NSString charactersIgnoringModifiers() {
    long result = OS.objc_msgSend(this.id, sel_charactersIgnoringModifiers);
    return result != 0 ? new NSString(result) : null;
  }
}
