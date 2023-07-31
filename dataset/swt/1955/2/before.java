class PlaceHold {
  public NSString characters() {
    int result = OS.objc_msgSend(this.id, sel_characters);
    return result != 0 ? new NSString(result) : null;
  }
}
