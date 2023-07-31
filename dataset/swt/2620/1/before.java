class PlaceHold {
  public static NSColor alternateSelectedControlColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_alternateSelectedControlColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
