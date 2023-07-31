class PlaceHold {
  public static NSValue valueWithSize(NSSize size) {
    int result = OS.objc_msgSend(class_NSNumber, sel_valueWithSize_, size);
    return result != 0 ? new NSValue(result) : null;
  }
}
