class PlaceHold {
  public NSPoint locationInWindow() {
    NSPoint result = new NSPoint();
    OS.objc_msgSend_stret(result, this.id, sel_locationInWindow);
    return result;
  }
}
