class PlaceHold {
  public static NSValue valueWithPoint(NSPoint point) {
    long result = OS.objc_msgSend(class_NSValue, sel_valueWithPoint_, point);
    return result != 0 ? new NSValue(result) : null;
  }
}
