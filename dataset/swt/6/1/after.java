class PlaceHold {
  public static NSColor disabledControlTextColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_disabledControlTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
