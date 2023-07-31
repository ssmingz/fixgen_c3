class PlaceHold {
  public static NSCursor crosshairCursor() {
    long result = OS.objc_msgSend(class_NSCursor, sel_crosshairCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
