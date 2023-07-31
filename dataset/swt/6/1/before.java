class PlaceHold {
  public static NSColor disabledControlTextColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_disabledControlTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
