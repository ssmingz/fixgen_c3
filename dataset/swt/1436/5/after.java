class PlaceHold {
  public static NSCursor resizeLeftCursor() {
    long result = OS.objc_msgSend(class_NSCursor, sel_resizeLeftCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
