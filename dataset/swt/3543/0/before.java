class PlaceHold {
  public static NSColor controlBackgroundColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_controlBackgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
