class PlaceHold {
  public static NSValue valueWithRange(NSRange range) {
    long result = OS.objc_msgSend(class_NSNumber, sel_valueWithRange_, range);
    return result != 0 ? new NSValue(result) : null;
  }
}
