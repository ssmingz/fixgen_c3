class PlaceHold {
  public static NSCursor arrowCursor() {
    long result = OS.objc_msgSend(class_NSCursor, sel_arrowCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
