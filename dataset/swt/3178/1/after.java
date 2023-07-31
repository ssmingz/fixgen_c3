class PlaceHold {
  public static NSValue valueWithSize(NSSize size) {
    long result = OS.objc_msgSend(class_NSNumber, sel_valueWithSize_, size);
    return result != 0 ? new NSValue(result) : null;
  }
}
