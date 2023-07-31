class PlaceHold {
  public NSCursor documentCursor() {
    int result = OS.objc_msgSend(this.id, sel_documentCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
