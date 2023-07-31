class PlaceHold {
  public NSArray objectValues() {
    int result = OS.objc_msgSend(this.id, sel_objectValues);
    return result != 0 ? new NSArray(result) : null;
  }
}
