class PlaceHold {
  public NSPoint convertScreenToBase(NSPoint aPoint) {
    NSPoint result = new NSPoint();
    OS.objc_msgSend_stret(result, this.id, sel_convertScreenToBase_1, aPoint);
    return result;
  }
}
