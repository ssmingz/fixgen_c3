class PlaceHold {
  public static NSColor alternateSelectedControlColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_alternateSelectedControlColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
