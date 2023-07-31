class PlaceHold {
  public static NSCursor resizeRightCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_resizeRightCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
