class PlaceHold {
  public NSPasteboard draggingPasteboard() {
    int result = OS.objc_msgSend(this.id, sel_draggingPasteboard);
    return result != 0 ? new NSPasteboard(result) : null;
  }
}
