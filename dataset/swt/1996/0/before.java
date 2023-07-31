class PlaceHold {
  public static NSValue valueWithPoint(NSPoint point) {
    int result = OS.objc_msgSend(class_NSNumber, sel_valueWithPoint_, point);
    return result != 0 ? new NSValue(result) : null;
  }
}
