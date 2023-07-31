class PlaceHold {
  public NSToolbar toolbar() {
    long result = OS.objc_msgSend(this.id, sel_toolbar);
    return result != 0 ? new NSToolbar(result) : null;
  }
}
