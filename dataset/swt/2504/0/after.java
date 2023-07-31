class PlaceHold {
  public NSView documentView() {
    long result = OS.objc_msgSend(this.id, sel_documentView);
    return result != 0 ? new NSView(result) : null;
  }
}
