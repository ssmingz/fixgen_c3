class PlaceHold {
  public NSColor backgroundColor() {
    long result = OS.objc_msgSend(this.id, sel_backgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
