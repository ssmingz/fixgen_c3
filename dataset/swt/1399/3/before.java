class PlaceHold {
  public NSPoint convertBaseToScreen(NSPoint aPoint) {
    NSPoint result = new NSPoint();
    OS.objc_msgSend_stret(result, this.id, sel_convertBaseToScreen_1, aPoint);
    return result;
  }
}
