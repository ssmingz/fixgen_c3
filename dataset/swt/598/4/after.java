class PlaceHold {
  public NSWindow keyWindow() {
    long result = OS.objc_msgSend(this.id, sel_keyWindow);
    return result != 0 ? new NSWindow(result) : null;
  }
}
