class PlaceHold {
  public static NSCursor resizeDownCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_resizeDownCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
