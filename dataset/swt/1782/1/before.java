class PlaceHold {
  public static NSCursor IBeamCursor() {
    int result = OS.objc_msgSend(class_NSCursor, sel_IBeamCursor);
    return result != 0 ? new NSCursor(result) : null;
  }
}
