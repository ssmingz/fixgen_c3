class PlaceHold {
  public static NSCursor arrowCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_arrowCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
