class PlaceHold {
  public static NSCursor currentCursor() {
    long result = OS.objc_msgSend(class_NSCursor, sel_currentCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
