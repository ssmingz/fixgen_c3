class PlaceHold {
  public NSView superview() {
    int result = OS.objc_msgSend(this.id, sel_superview);
    return result == this.id ? this : result != 0 ? new NSView(result) : null;
  }
}
