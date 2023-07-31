class PlaceHold {
  public NSScreen screen() {
    long result = OS.objc_msgSend(this.id, sel_screen);
    return result != 0 ? new NSScreen(result) : null;
  }
}
