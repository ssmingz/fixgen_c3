class PlaceHold {
  public static NSColor alternateSelectedControlTextColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_alternateSelectedControlTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
