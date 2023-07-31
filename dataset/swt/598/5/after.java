class PlaceHold {
  public NSPasteboard draggingPasteboard() {
    long result = OS.objc_msgSend(this.id, sel_draggingPasteboard);
    return result != 0 ? new NSPasteboard(result) : null;
  }
}
