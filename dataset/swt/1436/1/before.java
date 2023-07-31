class PlaceHold {
  public static NSCursor resizeUpCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_resizeUpCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
