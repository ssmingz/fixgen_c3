class PlaceHold {
  public static NSCursor resizeLeftCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_resizeLeftCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
