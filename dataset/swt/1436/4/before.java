class PlaceHold {
  public static NSCursor resizeUpDownCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_resizeUpDownCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
